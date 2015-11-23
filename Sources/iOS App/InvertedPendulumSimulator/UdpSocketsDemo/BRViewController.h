//
//  BRViewController.h
//  UdpSocketsDemo
//
//  Created by Eduardo Gutiérrez Silva on 07/05/14.
//  Copyright (c) 2014 Brachiosoft. All rights reserved.
//

#import <UIKit/UIKit.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <errno.h>
#define UDP 17
#import <CoreMotion/CoreMotion.h>
#import "MHRotaryKnob.h"
typedef struct sockaddr_in sockaddr_in;

@interface BRViewController : UIViewController{
    CGFloat lastMeasure;
    BOOL updateSensor;
    __weak IBOutlet UIButton *sendButton;
}

@property (strong, nonatomic) IBOutlet UITextField *txtIpAddress;
@property (strong, nonatomic) CMMotionManager *motionManager;
@property (weak, nonatomic) IBOutlet UILabel *robotAngle;
@property (weak, nonatomic) IBOutlet UILabel *realAngle;

- (IBAction)sendMessage:(id)sender;
@property (weak, nonatomic) IBOutlet MHRotaryKnob *knobView;
@property (weak, nonatomic) IBOutlet MHRotaryKnob *realKnobView;
@property (weak, nonatomic) IBOutlet UITextField *portTextField;

@end
