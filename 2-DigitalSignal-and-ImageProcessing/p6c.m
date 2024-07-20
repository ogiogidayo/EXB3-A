clear, close all

image = imread('lena_g.bmp');
image = double(image);

sobel_x = [0 0 0; -1 0 1; 0 0 0];
sobel_y = [0 -1 0; 0 0 0; 0 1 0];

grad_x = conv2(image, sobel_x, 'same');
grad_y = conv2(image, sobel_y, 'same');

bias_value = 127;
grad_x1 = grad_x + bias_value;
grad_y1 = grad_y + bias_value;

grad_magnitude = sqrt(grad_x.^2 + grad_y.^2);

figure;
subplot(2, 2, 1);
imshow(image, []);
title('元の画像');

subplot(2,2,2);
imshow(grad_x1, []);
title('水平部分微分');

subplot(2,2,3);
imshow(grad_y1, []);
title('垂直部分微分');

subplot(2,2,4);
imshow(grad_magnitude, []);
title('勾配の大きさ');
