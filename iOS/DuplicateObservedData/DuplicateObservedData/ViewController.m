//
//  ViewController.m
//  DuplicateObservedData
//
//  Created by admin on 2018/3/29.
//  Copyright © 2018年 liushuang.library. All rights reserved.
//

#import "ViewController.h"
#import "InternalLogic.h"

@interface ViewController () <UITextFieldDelegate,InternalLogicDelegate>

@property (nonatomic, strong) InternalLogic *internalLogic;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
}
#pragma - mark 文本框代理方法

-(void)textFieldDidEndEditing:(UITextField *)textField{
    if (textField == _startField) {
        self.startStr = _startField.text;
        
    }else if (textField == _endField){
        self.endStr = _endField.text;
       
    }else if(textField == _lengthField){
        self.lengthStr = _lengthField.text;
    }
}

#pragma -mark 业务逻辑模块
- (InternalLogic *)internalLogic{
    if (_internalLogic == nil) {
        _internalLogic = [InternalLogic new];
        _internalLogic.delegate = self;
    }
    return _internalLogic;
}

- (NSString *)startStr{
    return self.internalLogic.strEnd;
}
- (void)setStartStr:(NSString *)startStr{
    [self.internalLogic setStrStart:startStr];
}

- (NSString *)endStr{
    return self.internalLogic.strEnd;
}
-(void)setEndStr:(NSString *)endStr{
    [self.internalLogic setStrEnd:endStr];
}

- (void)setLengthStr:(NSString *)lengthStr{
    [self.internalLogic setStrLength:lengthStr];
}
- (NSString *)lengthStr{
    return [self.internalLogic strLength];
}
-(void)updateStrEnd:(InternalLogic *)internalLogic stringEnd:(NSString *)strEnd{
    [self.endField setText:strEnd];
}
-(void)updateStrLength:(InternalLogic *)internalLogic StringLength:(NSString *)strLength{
    [self.lengthField setText:strLength];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
