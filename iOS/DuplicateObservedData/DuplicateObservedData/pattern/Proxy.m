//
//  Proxy.m
//  PatternTest
//
//  Created by admin on 2018/2/13.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "Proxy.h"
#import "HouseHolder.h"

@interface Proxy()
@property (nonatomic) HouseHolder *houseHolder;
@end

@implementation Proxy


-(HouseHolder *)houseHolder{
    if (!_houseHolder) {
        _houseHolder = [HouseHolder new];
    }
    return _houseHolder;
}

- (void)preRentOut{
    NSLog(@"preRentOut");
}

- (void)postRentOut{
    NSLog(@"postRentOut");
}

- (void)rentHourseOfHouseHolder {
    [self preRentOut];
    [self.houseHolder rentHourseOfHouseHolder];
    [self postRentOut];
    
    
}

- (void)OCDelegateupdateView:(OCDelegate *)oCDelegate {
    NSLog(@"oCDelegate:%@",oCDelegate);
    
//    oCDelegate.PrintBlock = ^(NSString *str) {
//        NSLog(@"%@",str);
//    };
   
}



@end
