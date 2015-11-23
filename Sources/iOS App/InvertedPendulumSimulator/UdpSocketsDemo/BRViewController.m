//
//  BRViewController.m
//  UdpSocketsDemo
//
//  Created by Eduardo Gutiérrez Silva on 07/05/14.
//  Copyright (c) 2014 Brachiosoft. All rights reserved.
//
#import "BRViewController.h"
#import <CoreMotion/CoreMotion.h>
#import "MHRotaryKnob.h"
#import <QuartzCore/QuartzCore.h>
@interface BRViewController ()

@end

@implementation BRViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    [sendButton.layer setCornerRadius:15.0f];
    [sendButton.layer setBorderColor:[UIColor blueColor].CGColor];
    [sendButton.layer setBorderWidth:1.0f];
    
    [self setRotaryKnob:self.knobView];
    [self setRotaryKnob:self.realKnobView];
    
    lastMeasure = 0;
    updateSensor = NO;
    
    //Start MotionUpdate
    
    self.motionManager = [[CMMotionManager alloc] init];
    self.motionManager.deviceMotionUpdateInterval = 0.01;
    [self.motionManager startDeviceMotionUpdatesToQueue:[NSOperationQueue mainQueue] withHandler:^(CMDeviceMotion *devMotion, NSError *error) {
        float pitch =  (180/M_PI)*self.motionManager.deviceMotion.attitude.pitch;
        if (updateSensor == YES){
            [self sendAccelerometer:pitch];
        }
        if (ABS(lastMeasure - pitch) > 1.0){
            [self.knobView setValue:pitch animated:YES];
            lastMeasure = pitch;
        }
        [self.realKnobView setValue:pitch / 5.0 animated:YES];
        [self.robotAngle setText:[NSString stringWithFormat:@"%f", pitch / 5.0]];
        [self.realAngle setText:[NSString stringWithFormat:@"%f", pitch]];
        
    }];
}

- (void)setRotaryKnob:(MHRotaryKnob*)knob{
    knob.interactionStyle = MHRotaryKnobInteractionStyleRotating;
    knob.scalingFactor = 1.5;
    [knob setMinRequiredDistanceFromKnobCenter:50.0];
    [knob setMaxAngle:90.0];
    [knob setEnabled:YES];
    [knob setUserInteractionEnabled:NO];
    knob.maximumValue = 90;
    knob.minimumValue = -90;
    knob.value = 10;
    knob.defaultValue = 10;
    knob.resetsToDefault = YES;
    knob.backgroundColor = [UIColor clearColor];
    knob.backgroundImage = [UIImage imageNamed:@"360Circle"];
    [knob setKnobImage:[UIImage imageNamed:@"needle"] forState:UIControlStateNormal];
    [knob setKnobImage:[UIImage imageNamed:@"needle"] forState:UIControlStateDisabled];
}

- (NSString*)adaptSensorValue:(float)data{
    NSString *stringToSend = [[NSString alloc] init];
    
    stringToSend = [NSString stringWithFormat:@"0, 0, 0, 0, %f", -data];
    return stringToSend;
}

- (void)sendAccelerometer:(float)data{
    NSString *stringToSend;
    stringToSend = [self adaptSensorValue:data];
    // Get IP Address
    NSString *ipAddrs = self.txtIpAddress.text;
    
    // Get Message
    NSString *message = stringToSend;
    
    // Our dialog.
    
    // DEST Address
    sockaddr_in *dest_addr = (sockaddr_in*)malloc(sizeof(sockaddr_in));
    
    // Init address
    memset(dest_addr, 0, sizeof(sockaddr_in));
    dest_addr->sin_family = AF_INET;
    
    // Get Source.
    
    // Load address.
    int pton_val = inet_pton(AF_INET, [ipAddrs UTF8String], &(dest_addr->sin_addr.s_addr));
    
    // Setting port
    dest_addr->sin_port=htons([self.portTextField.text integerValue]);
    
    // Create our socket.
    int sock_fd = socket(AF_INET, SOCK_DGRAM, UDP);
    
    // Error handling.
    if(sock_fd < 0){
        perror("socket");
        // Message Box with error.
    }else{  // Our socket was opened
        // Get Message Data
        NSData *bytes = [message dataUsingEncoding:NSUTF8StringEncoding];
        uint8_t * rawBytes = (uint8_t*)[bytes bytes];
        
        // Send our message.
        int error = sendto(sock_fd, rawBytes, [message length], 0, (struct sockaddr*)dest_addr, sizeof(sockaddr_in));
        if(error < 0){
            
        }else{
            close(sock_fd);
        }
    }
    
}
- (IBAction)sendMessage:(id)sender {
    if ([sendButton.titleLabel.text isEqualToString:@"Send"]){
        [sendButton setTitle:@"Stop" forState:UIControlStateNormal];
        updateSensor = YES;
    } else {
        updateSensor = NO;
        [sendButton setTitle:@"Send" forState:UIControlStateNormal];
    }
}
@end
