//
//  InternalLogic.m
//  DuplicateObservedData
//
//  Created by admin on 2018/3/29.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "InternalLogic.h"

@interface InternalLogic()

@end

@implementation InternalLogic


- (instancetype)init
{
    self = [super init];
    if (self) {
        _strEnd = @"0";
        _strStart = @"0";
        _strLength = @"0";
    }
    return self;
}
-(void)setStrStart:(NSString *)strStart{
    _strStart = strStart;
    [self calculateLength];
}

-(void)setStrEnd:(NSString *)strEnd{
    _strEnd = strEnd;
    [self calculateLength];
}
- (void)setStrLength:(NSString *)strLength{
    _strLength = strLength;
    [self calculateEnd];
}

- (void)calculateLength {
    NSInteger start = _strStart.integerValue;
    NSInteger end = _strEnd.integerValue;
    _strLength = [NSString stringWithFormat:@"%d",(int)(end - start)];
    
    if (_delegate && [_delegate respondsToSelector:@selector(updateStrLength:StringLength:)]) {
        [_delegate updateStrLength:self StringLength:_strLength];
    }
}
- (void)calculateEnd {
    NSInteger start = _strStart.integerValue;
    NSInteger length = _strLength.integerValue;
    _strEnd = [NSString stringWithFormat:@"%d",(int)(length + start)];
    if (_delegate && [_delegate respondsToSelector:@selector(updateStrEnd:stringEnd:)]) {
        [_delegate updateStrEnd:self stringEnd:_strEnd];
    }
}
@end
