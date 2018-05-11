//
//  StraightInsertionSort.h
//  PatternTest
//
//  Created by admin on 2018/5/11.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <Foundation/Foundation.h>
#define INITSIZE 20
typedef long KeyType;
typedef struct {
    KeyType key;
    char *otherInfo;
}RedType;
typedef struct {
    RedType r[INITSIZE +1];
    int length;
}SqList;

@interface StraightInsertionSort : NSObject
- (void)initSortList:(SqList*) list;
@end
