//
//  SqStack.m
//  DuplicateObservedData
//
//  Created by 刘双 on 2018/9/27.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "SqStack.h"

@implementation SqStack
void initStack(SqStacks *s) {
    if (!(s->base = (int *)malloc(KMAXLENGTH * sizeof(SqStacks)))) {
        s->base = s->top;
        s->length = KMAXLENGTH;
    }
}
void DestoryStack(SqStacks *s) {
    free(s->base);
    s->base = NULL;
    s->top = NULL;
    s->length = 0;
}
void clearStack(SqStacks *s) {
    s->top = s->base;
}
int stackLength(SqStacks *s) {
    return (int)(s->top-s->base);
}
int getTop(SqStacks *s) {
    if (s->base == s->top) {
        return -1;
    }
    return *s->top--;
}
void push(SqStacks *s, int *e) {
    if (s->top-s->base >= KMAXLENGTH) {
        s->base = (int *)realloc(s->base, (s->length + KMAXLENGTH)*sizeof(SqStacks));
        s->top = s->base+s->length;
        s->length += KMAXLENGTH;
    }
    *(s->top)++ = *e;
}
int pop (SqStacks *s, int *e) {
    if (s->base == s->top) {
        return -1;
    }
    *e = *--s->top;
    return 0;
}

@end
