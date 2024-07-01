clear, close all

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
I = imread('girl.bmp');

gamma = 0.5;

I_corrected = uint8(255 * (double(I)/255).^gamma);

x = 0:255;
y = uint8(255 * (x/255).^gamma);

figure;

subplot(2,2,2);
plot(x, y);
grid on;
title('ガンマ補正トーンカーブ (γ = 0.5)');
xlabel('入力値');
ylabel('出力値');

subplot(2,2,1);
imshow(I);
title('元画像');

subplot(2,2,3);
imshow(I_corrected);
title('ガンマ補正後の画像');
[pixel_values, frequencies] = createHistogram(I_corrected);
subplot(2,2,4);
% [counts, bins] = histcounts(I_corrected(:), 256);
% bar(bins(1:end-1), counts);
% imhist(I_corrected);
bar(pixel_values, frequencies);
ylim('auto');
grid on;
title('ガンマ補正後の画像のヒストグラム');
% xlabel('ピクセル強度');
% ylabel('頻度');