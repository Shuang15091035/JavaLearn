//
//  DelegateTest.m
//  PatternTest
//
//  Created by admin on 2018/2/13.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "HouseHolder.h"

@interface HouseHolder() 
@property (nonatomic, strong) NSMutableArray *mutableArr;
@end

@implementation HouseHolder

- (void)setState:(NSString *)state{
    if (_state != state) {
        _state = state;
    }
}

- (NSMutableArray *)mutableArr{
    if (_mutableArr == nil) {
        _mutableArr = [NSMutableArray array];
    }
    return _mutableArr;
}

- (void)addObserver:(NSObject *)observer forKeyPath:(NSString *)keyPath options:(NSKeyValueObservingOptions)options context:(nullable void *)context{
    [self.mutableArr addObject:observer];
    self.state = keyPath;
    NSLog(@"customer + addObserver:");
}

- (void)removeObserver:(NSObject *)observer forKeyPath:(NSString *)keyPath context:(nullable void *)context API_AVAILABLE(macos(10.7), ios(5.0), watchos(2.0), tvos(9.0)){
    [self.mutableArr removeObject:observer];
}

- (void)removeObserver:(NSObject *)observer forKeyPath:(NSString *)keyPath{
    [self.mutableArr removeObject:observer];
}

- (void)observeValueForKeyPath:(nullable NSString *)keyPath ofObject:(nullable id)object change:(nullable NSDictionary<NSKeyValueChangeKey, id> *)change context:(nullable void *)context{
    for (id obj in self.mutableArr) {
        [obj observeValueForKeyPath:_state ofObject:self change:change context:nil];
    }
    NSLog(@"customer + observeValueForKeyPath");
}

- (void)rentHourseOfHouseHolder {
    
}

@end
