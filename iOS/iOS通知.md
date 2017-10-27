iOS :本地通知和远程通知
本地通知：
###1.需要在应用启动方法中配置
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
        if ([[[UIDevice currentDevice]systemVersion] integerValue] >= 8.0) {
        UIUserNotificationSettings *setting = [UIUserNotificationSettings settingsForTypes:UIUserNotificationTypeBadge | UIUserNotificationTypeAlert | UIUserNotificationTypeSound categories:nil];
        [[UIApplication sharedApplication] registerUserNotificationSettings:setting];
        }
}
###2.添加通知
- (void)createLocalNotification {
UILocalNotification *localNotification = [[UILocalNotification alloc]init];
localNotification.fireDate = [NSDate dateWithTimeIntervalSinceNow:10];

//通知内容
localNotification.alertBody = @"你好呀";
//锁屏界面的文字
localNotification.alertAction = @"具体的消息";
//锁屏界面操作是否有效
localNotification.hasAction = YES;

localNotification.alertTitle = @"222222";
localNotification.category = @"category";
[localNotification setApplicationIconBadgeNumber:10];
localNotification.userInfo = @{@"name" : @"张三", @"toName" : @"李四"};
[[UIApplication sharedApplication] scheduleLocalNotification:localNotification];
}
