clear, close all
% 画像の読み込み
I = imread('girl.bmp');

% ネガティブ変換
I_max = 255; % 8ビット画像を想定
I_negative = I_max - I;

% 画像の表示
figure;
image(I_negative);
colormap(gray(256));
title('girl.bmpのネガティブ画像');
axis image;