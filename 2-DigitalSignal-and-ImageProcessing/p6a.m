clear, close all

noise01 = imread('noise01.bmp');
noise02 = imread('noise02.bmp');

h = fspecial('average', [3 3]);

filtered_noise01 = filter2(h, noise01);
filtered_noise02 = filter2(h, noise02);

figure;
figure;
subplot(2, 2, 1), imshow(noise01), title('noise01.bmp');
subplot(2, 2, 2), imshow(noise02), title('noise02.bmp');
subplot(2, 2, 3), imshow(uint8(filtered_noise01)), title('Filtered Noise 01');
subplot(2, 2, 4), imshow(uint8(filtered_noise02)), title('Filtered Noise 02');