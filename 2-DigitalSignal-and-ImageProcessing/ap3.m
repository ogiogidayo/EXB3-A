clear, close all

I = imread('girl.bmp');

J_histeq = histeq(I);

J_adapthist = adapthisteq(I);
figure('Position', [100, 100, 1200, 800]);

subplot(2, 3, 1);
imshow(I);
title('Original Image');

subplot(2, 3, 4);
imhist(I);
title('Histogram of Original Image');

subplot(2, 3, 2);
imshow(J_histeq);
title('Standard Histogram Equalization');

subplot(2, 3, 5);
imhist(J_histeq);
title('Histogram of Standard Equalized Image');

subplot(2, 3, 3);
imshow(J_adapthist);
title('Adaptive Histogram Equalization');

subplot(2, 3, 6);
imhist(J_adapthist);
title('Histogram of Adaptive Equalized Image');


% Save the figure (optional)
% saveas(gcf, 'histogram_equalization_comparison.png');

% I = imread('girl.bmp');
% J = histeq(I);
% 
% figure('Position', [100, 100, 1000, 800]);
% 
% subplot(2, 2, 1);
% imshow(I);
% title('Original Image');
% 
% subplot(2, 2, 3);
% imhist(I);
% title('Histogram of Original Image');
% 
% subplot(2, 2, 2);
% imshow(J);
% title('Equalized Image');
% 
% subplot(2, 2, 4);
% imhist(J);
% title('Histogram of Equalized Image');
