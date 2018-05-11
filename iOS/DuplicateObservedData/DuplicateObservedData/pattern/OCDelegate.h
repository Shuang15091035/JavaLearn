//
//  OCDelegate.h
//  PatternTest
//
//  Created by admin on 2018/2/26.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <Foundation/Foundation.h>
@class OCDelegate;


//typedef void(^PrintBLock)(NSString *str);
@protocol OCDelegateProtocol <NSObject>
- (void) OCDelegateupdateView:(OCDelegate*) oCDelegate;
@end

@interface OCDelegate : NSObject
@property (nonatomic, weak) id<OCDelegateProtocol> deleagte;

//@property (nonatomic, copy) void(^PrintBlock)(NSString *str);
- (void)methodPrintBlock:(void(^)(NSString *str)) myValue;
@end


