//
//  Queue.m
//  DuplicateObservedData
//
//  Created by 刘双 on 2018/9/27.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "Queue.h"

@implementation Queue
void initQueue(LinkQueue *linkQueue) {
    if (!(linkQueue->front = linkQueue->rear = (QueuePtr*)malloc(sizeof(QueuePtr)))) {
        exit(-1);
    }
    linkQueue->front->next = NULL;
}

void enQueue(LinkQueue *linkQueue) {
    int data;
    scanf("%d",&data);
    while (data != 999) {
        linkQueue->rear->next = (QueuePtr*)malloc(sizeof(QueuePtr));
        linkQueue->rear->next->data = data;
        linkQueue->rear = linkQueue->rear->next;
        linkQueue->rear->next = NULL;
        scanf("%d",&data);
    }
}
void DeQueue(LinkQueue *linkQueue,int *e) {
    if (linkQueue->front == linkQueue->rear) {
        *e = -1;
    }
    QueuePtr *p = linkQueue->front->next;
    linkQueue->front->next = p->next;
    *e = p->data;
    free(p);
}
@end
