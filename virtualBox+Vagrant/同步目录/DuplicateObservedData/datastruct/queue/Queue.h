//
//  Queue.h
//  DuplicateObservedData
//
//  Created by 刘双 on 2018/9/27.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef struct QNode {
    int data;
    struct QNode * next;
} QueuePtr;

typedef struct{
    QueuePtr *front,*rear;
}LinkQueue;

@interface Queue : NSObject
void initQueue(LinkQueue *linkQueue) ;
void enQueue(LinkQueue *linkQueue) ;
void DeQueue(LinkQueue *linkQueue,int *e) ;
@end

NS_ASSUME_NONNULL_END
