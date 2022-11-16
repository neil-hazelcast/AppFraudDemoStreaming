# AppFraudDemoStreaming

The streaming portion of the AppFraudDemo.  This creates a pipeline which listens to a specific map (that thet client is setup to write to) and will read any values passed in and run that through this pipeline.  This pipeline will run a ML model on the incoming app and write that out to a response map.  The client will listen for a response to it's request and once received show those results.
