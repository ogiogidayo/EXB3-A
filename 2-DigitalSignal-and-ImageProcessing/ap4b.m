clear, close all

img = imread('girl.bmp');

Gx_sobel = [-1 0 1; -2 0 2; -1 0 1];
Gy_sobel = [-1 -2 -1; 0 0 0; 1 2 1];

sobel_x = imfilter(double(img), Gx_sobel, 'replicate');
sobel_y = imfilter(double(img), Gy_sobel, 'replicate');
sobel_img = sqrt(sobel_x.^2 + sobel_y.^2);

figure;

subplot(2,2,1);
imshow(img);
title('Original Image');

subplot(2,2,2);
imshow(sqrt(sobel_x.^2), []);
title('Sobel Filter (X direction)');

subplot(2,2,3);
imshow(sqrt(sobel_y.^2), []);
title('Sobel Filter (Y direction)');

subplot(2,2,4);
imshow(sobel_img, []);
title('Sobel Filter (Combined)');
