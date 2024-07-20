clear, close all
I = imread('lena_g.bmp');

[Gx, Gy] = imgradientxy(I);

Gx_display = uint8(Gx + 127);
Gy_display = uint8(Gy + 127);

[Gmag, ~] = imgradient(Gx, Gy);

laplacian_filter = [0 1 0; 1 -4 1; 0 1 0];

laplacian = imfilter(double(I), laplacian_filter, 'replicate');

sharpened = double(I) - laplacian;

figure;
subplot(2,2,1), imshow(I), title('元画像');
subplot(2,2,2), imshow(uint8(laplacian + 127)), title('ラプラシアン');
subplot(2,2,3), imshow(uint8(sharpened)), title('シャープニング後');