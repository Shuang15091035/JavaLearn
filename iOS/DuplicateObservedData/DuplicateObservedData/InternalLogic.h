//
//  InternalLogic.h
//  DuplicateObservedData
//
//  Created by admin on 2018/3/29.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <Foundation/Foundation.h>
@class InternalLogic;
@protocol InternalLogicDelegate <NSObject>
- (void)updateStrStart:(InternalLogic *) internalLogic stringStart:(NSString *)strStart;
- (void)updateStrEnd:(InternalLogic *) internalLogic stringEnd:(NSString *)strEnd;
- (void)updateStrLength:(InternalLogic *) internalLogic StringLength:(NSString *)strLength;
@end

@interface InternalLogic : NSObject
@property (nonatomic, weak) id<InternalLogicDelegate> delegate;
@property (nonatomic, strong) NSString *strStart;
@property (nonatomic, strong) NSString *strEnd;
@property (nonatomic, strong) NSString *strLength;
@end
