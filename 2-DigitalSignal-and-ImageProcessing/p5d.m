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

min_val = double(min(I(:)));
max_val = double(max(I(:)));

I_stretched = uint8(255 * (double(I) - min_val) / (max_val - min_val));

figure('Position', [100, 100, 800, 800]);

subplot(2,2,1);
imshow(I);
title('元の画像');

subplot(2,2,2);
imshow(I_stretched);
title('Linear contrast stretching後の画像');

subplot(2,2,3);
plot([min_val, max_val], [0, 255], 'r-', 'LineWidth', 2);
xlim([0 255]);
ylim([0 255]);
title('変換曲線');
xlabel('入力値');
ylabel('出力値');

grid on;

subplot(2,2,4);
% imhist(I_stretched);
[pixel_values, frequencies] = createHistogram(I_stretched);
bar(pixel_values, frequencies);
ylim('auto');
title('Linear contrast stretching後のヒストグラム');


