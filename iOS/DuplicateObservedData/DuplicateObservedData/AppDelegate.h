//
//  AppDelegate.h
//  DuplicateObservedData
//
//  Created by admin on 2018/3/29.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef struct BiTNode {
    struct BiTNode *left,*rigth;
    int data;
}BiTNode,*BiTree;

@interface AppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;


@end

