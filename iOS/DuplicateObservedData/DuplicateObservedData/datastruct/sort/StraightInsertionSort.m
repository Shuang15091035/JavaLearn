//
//  StraightInsertionSort.m
//  PatternTest
//
//  Created by admin on 2018/5/11.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "StraightInsertionSort.h"



@interface StraightInsertionSort()

@end

@implementation StraightInsertionSort

- (void)initSortList:(SqList*) list {
    list->length = INITSIZE;
    for (int i = 1; i < list->length + 1; i++) {
        list->r[i].key = arc4random_uniform(30);
    }
    for (int i = 1; i < list->length+1; i++) {
        NSLog(@"%ld",list->r[i].key);
    }
}

-(void)insertSort:(SqList*) list {
    int i,j;
    for (i = 2; i < list->length; i++) {
        if (list->r[i].key > list->r[i-1].key) {
            list->r[0] = list->r[i];
            list->r[i] = list->r[i-1];
            for (j = i-2; list->r[0].key > list->r[j].key; j--) {
                list->r[j+1] = list->r[j];
            }
            list->r[j+1] = list->r[0];
        }
    }
}

bool greaterThan(KeyType elem1, KeyType elem2){
    return elem1 > elem2;
}
@end
