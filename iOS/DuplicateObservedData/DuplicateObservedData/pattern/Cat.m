//
//  Cat.m
//  PatternTest
//
//  Created by admin on 2018/2/26.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "Cat.h"

@implementation Cat
- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary<NSKeyValueChangeKey,id> *)change context:(void *)context{
     NSLog(@"%s",__func__);
}
@end
