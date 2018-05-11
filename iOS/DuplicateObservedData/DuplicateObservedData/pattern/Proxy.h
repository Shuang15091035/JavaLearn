//
//  Proxy.h
//  PatternTest
//
//  Created by admin on 2018/2/13.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RentProtocol.h"
#import "OCDelegate.h"

@interface Proxy : NSObject <RentProtocol,OCDelegateProtocol>

@end
