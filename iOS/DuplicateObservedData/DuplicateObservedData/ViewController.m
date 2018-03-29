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
@property (nonatomic, strong) NSString *startStr;
@property (nonatomic, strong) NSString *endStr;
@property (nonatomic, strong) NSString *lengthStr;
@property (nonatomic, strong) InternalLogic *internalLogic;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.internalLogic = [InternalLogic new];
    self.internalLogic.delegate = self;
}
-(void)textFieldDidEndEditing:(UITextField *)textField{
    if (textField == _startField) {
        self.startStr = _startField.text;
        
    }else if (textField == _endField){
        self.endStr = _endField.text;
       
    }else if(textField == _lengthField){
        self.lengthStr = _lengthField.text;
    }
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/
- (NSString *)startStr{
    return _internalLogic.strEnd;
}
- (void)setStartStr:(NSString *)startStr{
    [_internalLogic setStrStart:startStr];
}

- (NSString *)endStr{
    return _internalLogic.strEnd;
}
-(void)setEndStr:(NSString *)endStr{
    [_internalLogic setStrEnd:endStr];
}

- (void)setLengthStr:(NSString *)lengthStr{
    [_internalLogic setStrLength:lengthStr];
}
- (NSString *)lengthStr{
    return [_internalLogic strLength];
}
-(void)updateStrEnd:(InternalLogic *)internalLogic stringEnd:(NSString *)strEnd{
    [self.endField setText:strEnd];
}
-(void)updateStrLength:(InternalLogic *)internalLogic StringLength:(NSString *)strLength{
    [self.lengthField setText:strLength];
}
@end
