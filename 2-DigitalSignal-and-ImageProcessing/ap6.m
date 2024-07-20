clear, close all

digitDatasetPath = fullfile(matlabroot, 'toolbox', 'nnet', 'nndemos', 'nndatasets', 'DigitDataset');
digitData = imageDatastore(digitDatasetPath, 'IncludeSubfolders', true, 'LabelSource', 'foldernames');


CountLabel = digitData.countEachLabel;


trainingNumFiles = 750;
rng(1);
[trainDigitData, testDigitData] = splitEachLabel(digitData, trainingNumFiles, 'randomize');

% layers1 = [
%     imageInputLayer([28 28 1])
%     convolution2dLayer(3, 8)
%     reluLayer
%     maxPooling2dLayer(2, 'Stride', 2)
%     fullyConnectedLayer(10)
%     softmaxLayer
%     classificationLayer()
% ];
% 
options = trainingOptions('adam', ...
     'MaxEpochs', 15, ...
     'Plots', 'training-progress', ...
     'InitialLearnRate', 0.001);
% 
% net1 = trainNetwork(trainDigitData, layers1, options);
% 
% YTest = classify(net1, testDigitData);
% TTest = testDigitData.Labels;
% accuracy1 = sum(YTest == TTest) / numel(TTest);
% fprintf('Network 1 Classification accuracy: %.2f%%\n', accuracy1 * 100);
% 
% layers2 = [
%     imageInputLayer([28 28 1])
%     convolution2dLayer(5, 8)
%     reluLayer
%     maxPooling2dLayer(2, 'Stride', 2)
%     convolution2dLayer(5, 16)
%     reluLayer
%     fullyConnectedLayer(10)
%     softmaxLayer
%     classificationLayer()
% ];
% 
% net2 = trainNetwork(trainDigitData, layers2, options);
% 
% YTest = classify(net2, testDigitData);
% TTest = testDigitData.Labels;
% accuracy2 = sum(YTest == TTest) / numel(TTest);
% fprintf('Network 2 Classification accuracy: %.2f%%\n', accuracy2 * 100);

layers3 = [
    imageInputLayer([28 28 1])
    convolution2dLayer(3, 32, 'Padding', 'same')
    batchNormalizationLayer
    reluLayer
    maxPooling2dLayer(2, 'Stride', 2)
    convolution2dLayer(3, 64, 'Padding', 'same')
    batchNormalizationLayer
    reluLayer
    maxPooling2dLayer(2, 'Stride', 2)
    convolution2dLayer(3, 128, 'Padding', 'same')
    batchNormalizationLayer
    reluLayer
    fullyConnectedLayer(128)
    reluLayer
    fullyConnectedLayer(10)
    softmaxLayer
    classificationLayer()
];


net3 = trainNetwork(trainDigitData, layers3, options);

YTest = classify(net3, testDigitData);
TTest = testDigitData.Labels;
accuracy3 = sum(YTest == TTest) / numel(TTest);
fprintf('Network 3 Classification accuracy: %.30f%%\n', accuracy3 * 100);