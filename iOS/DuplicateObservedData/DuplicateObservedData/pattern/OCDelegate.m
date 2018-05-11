//
//  OCDelegate.m
//  PatternTest
//
//  Created by admin on 2018/2/26.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "OCDelegate.h"
#import "Proxy.h"

@interface OCDelegate()

@end

@implementation OCDelegate


- (void)userClickView{
    
    Proxy *proxy = [Proxy new];
    self.deleagte = proxy;
    if (_deleagte && [_deleagte respondsToSelector:@selector(OCDelegateupdateView:)]) {
        [_deleagte OCDelegateupdateView:self];
    }
    
}

@end
