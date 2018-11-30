#include <pthread.h>
#include <stdio.h>
#include <limits.h>
#include <unistd.h>
#include <string.h>


// metadata structure as header
struct metadata 
{
	size_t size;
	unsigned free;
	struct metadata *next;
};

struct metadata *head = NULL;
struct metadata *tail = NULL;
pthread_mutex_t locking;


// function to find first free block
struct metadata *find_first_free_block(size_t size)
{
	struct metadata *curr = head;
	while(curr) 
	{
		if (curr->free && curr->size >= size)
			return curr;
		curr = curr->next;
	}
	return NULL;
}


// my own free function 
void csl333_free(void *ptr)
{
	if (!ptr)
		return;
	struct metadata *header, *tmp;
	void *programbreak;

	pthread_mutex_lock(&locking);
	header = (struct metadata*)ptr - 1;
	programbreak = sbrk(0);

	if ((char*)ptr + header->size == programbreak) 
	{
		if (head == tail) 
		{
			head = tail = NULL;
		} else 
		{
			tmp = head;
			while (tmp) 
			{
				if(tmp->next == tail) 
				{
					tmp->next = NULL;
					tail = tmp;
				}
				tmp = tmp->next;
			}
		}
	
		sbrk(0 - header->size - sizeof(struct metadata));
		pthread_mutex_unlock(&locking);
		return;
	}
	header->free = 1;
	pthread_mutex_unlock(&locking);
}


// my own malloc function 
void *csl333_malloc(size_t size)
{
	if (!size)
		return NULL;
	if(size > INT_MAX || size < 0)
		return NULL;

	struct metadata *header;
	size_t total_size;
	void *ptr;
	
	pthread_mutex_lock(&locking);
	header = find_first_free_block(size);

	if (header) 
	{
		header->free = 0;
		pthread_mutex_unlock(&locking);
		return (void*)(header + 1);
	}

	total_size = sizeof(struct metadata) + size;
	ptr = sbrk(total_size);
	if (ptr == (void*) -1) 
	{
		pthread_mutex_unlock(&locking);
		return NULL;
	}
	header = ptr;
	header->size = size;
	header->free = 0;
	header->next = NULL;
	if (!head)
		head = header;
	if (tail)
		tail->next = header;
	tail = header;
	pthread_mutex_unlock(&locking);
	return (void*)(header + 1);
}


// my own realloc function 
void *csl333_realloc(void *ptr, size_t size)
{
	struct metadata *header;
	void *ret;

	// handling case 2 :
	if(ptr != NULL && size == 0)
	{
		csl333_free(ptr);
		return NULL;
	}

	// handling case 3,4 :
	else if (!ptr || !size)
		return csl333_malloc(size);
	header = (struct metadata*)ptr - 1;

	// handling cse 5 :
	if (header->size >= size)
		return ptr;
	ret = csl333_malloc(size);
	if (ret) 
	{
		memcpy(ret, ptr, header->size);
		csl333_free(ptr);
	}
	
	// handling case 1 :
	return ret;
}

