clear, close all

img = imread('girl.bmp');

Gx_prewitt = [-1 0 1; -1 0 1; -1 0 1];
Gy_prewitt = [-1 -1 -1; 0 0 0; 1 1 1];

prewitt_x = imfilter(double(img), Gx_prewitt, 'replicate');
prewitt_y = imfilter(double(img), Gy_prewitt, 'replicate');
prewitt_img = sqrt(prewitt_x.^2 + prewitt_y.^2);

figure;

subplot(2,2,1);
imshow(img);
title('Original Image');

subplot(2,2,2);
imshow(sqrt(prewitt_x.^2), []);
title('Prewitt Filter (X direction)');

subplot(2,2,3);
imshow(sqrt(prewitt_y.^2), []);
title('Prewitt Filter (Y direction)');

subplot(2,2,4);
imshow(prewitt_img, []);
title('Prewitt Filter (Combined)');