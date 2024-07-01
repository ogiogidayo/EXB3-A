clear, close all
% sincフィルタの定義
function y = sinc(x)
    y = ones(size(x));
    idx = x ~= 0;
    y(idx) = sin(pi*x(idx))./(pi*x(idx));
end

% sinc = @(t) sin(pi*t)./(pi*t +(t==0));

T1 = 0.8;
t_display = 0:0.1:10;
t_recover = -8:0.1:18;

x_c = @(t) 3*sinc(t-2.7) + sinc((t-7.5)/2);

t_sample = 0:T1:10;
x_sample = x_c(t_sample);

x_recover = zeros(size(t_recover));
for i = 1:length(t_recover)
    x_recover(i) = sum(x_sample .* sinc((t_recover(i) - t_sample)/T1));
end

figure;
plot(t_display, x_c(t_display), 'g-');
hold on;
plot(t_sample, x_sample, 'o', 'MarkerSize', 5);
plot(t_recover, x_recover, 'b.');
xlim([0 10]);
legend('元の信号', 'サンプル点', '復元された信号');
% legend('元の信号','復元された信号');
xlabel('時間 (t)');
ylabel('振幅');
title('連続時間信号の復元');
grid on;
