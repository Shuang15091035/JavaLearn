//
//  ReplaceConditionalWithPolymorphism.h
//  DuplicateObservedData
//
//  Created by admin on 2018/3/30.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum {
    Type_European,
    Type_African,
    Type_norwegianBlue
} Type;

@interface ReplaceConditionalWithPolymorphism : NSObject
- (void)getSpeed:(Type) type;
@end
