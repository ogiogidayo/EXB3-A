clear, close all
% 画像の読み込み
img = imread('girl.bmp');

function [p, h] = createHistogram(img)

    p = 0:255;
    
    h = zeros(1, 256);
    [hs, ws] = size(img);
    
    for j = 1:hs
        for i = 1:ws
            h(img(j, i) + 1) = h(img(j, i) + 1) + 1;
        end
    end
end

% % 画像のサイズを取得
% [rows, cols, channels] = size(img);
% 
% % グレースケールに変換（カラー画像の場合）
% if channels == 3
%     gray_img = rgb2gray(img);
% else
%     gray_img = img;
% end

% ヒストグラムデータの計算
% [counts, bins] = histcounts(gray_img(:), 256);

% ヒストグラムの表示
figure;
% imhist(img);
[pixel_values, frequencies] = createHistogram(img);
% bar(bins(1:end-1), counts);
bar(pixel_values, frequencies);
title('girl.bmpのヒストグラム');
xlabel('ピクセル強度');
ylabel('頻度');
ylim('auto');
grid on;