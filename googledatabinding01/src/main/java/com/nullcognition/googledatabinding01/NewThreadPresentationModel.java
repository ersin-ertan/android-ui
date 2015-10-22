package com.nullcognition.googledatabinding01;
// ersin 21/10/15 Copyright (c) 2015+ All rights reserved.


import android.os.HandlerThread;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class NewThreadPresentationModel extends HandlerThread{

	NewThreadPresentationModel(){
		super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
	}
}
