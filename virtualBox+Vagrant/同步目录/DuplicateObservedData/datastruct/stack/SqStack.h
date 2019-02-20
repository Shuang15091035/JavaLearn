//
//  SqStack.h
//  DuplicateObservedData
//
//  Created by 刘双 on 2018/9/27.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <Foundation/Foundation.h>
#define KMAXLENGTH 100

typedef struct sqstack {
    int *base;
    int *top;
    int length;
} SqStacks;

@interface SqStack : NSObject

@end

