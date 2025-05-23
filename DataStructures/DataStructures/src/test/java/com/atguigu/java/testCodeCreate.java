package com.atguigu.java;

/**
 * @author yangda
 * @create 2025-05-22-21:00
 * @description:
 */
public class testCodeCreate {
}
    // 生成随机的5位小写字母和数字组合
    function generateRandomCode(length = 5) {
    const characters = 'abcdefghijklmnopqrstuvwxyz0123456789';
        let result = '';
        for (let i = 0; i < length; i++) {
            result += characters.charAt(Math.floor(Math.random() * characters.length));
        }
        return result;
    }

    // 从响应文本中提取referral链接
    function extractReferralLink(text) {
        // 匹配形如https://cursor.com/referral?code=VWF8EBCTHVDT的链接
        // code是大写字母和数字的组合
    const regex = /https:\/\/cursor\.com\/referral\?code=[A-Z0-9]+/g;
    const matches = text.match(regex);
        return matches ? matches[0] : null;
    }

    // 创建一个全局变量来存储interval ID，便于停止
    let intervalId = null;

    // 停止函数
    function stopFetching() {
        if (intervalId !== null) {
            clearInterval(intervalId);
            console.log("已停止自动获取链接");
            intervalId = null;
        } else {
            console.log("没有正在运行的获取任务");
        }
    }

    // 主函数，每0.5秒执行一次
    function startFetching() {
        // 如果已经在运行，先停止
        if (intervalId !== null) {
            stopFetching();
        }

        // 使用当前页面的cookie
    const cookies = document.cookie;

        // 使用当前页面URL作为referer
    const referer = window.location.href;

        console.log("开始执行，每0.5秒尝试一次...");
        console.log("输入 stopFetching() 可以停止执行");

        // 使用setInterval定期执行
        intervalId = setInterval(async () => {
        // 生成随机五位码
        const randomCode = generateRandomCode();

        try {
            // 构建URL，将rsc参数替换为随机生成的代码
            const url = `https://www.perplexity.ai/account/pro-perks?_rsc=${randomCode}`;

            // 发送请求
            const response = await fetch(url, {
                    "headers": {
                "accept": "*/*",
                        "accept-language": "en-US,en;q=0.9",
                        "next-router-state-tree": "%5B%22%22%2C%7B%22children%22%3A%5B%22(client)%22%2C%7B%22children%22%3A%5B%22(no-sidebar)%22%2C%7B%22children%22%3A%5B%22(settings)%22%2C%7B%22children%22%3A%5B%22account%22%2C%7B%22children%22%3A%5B%22pro-perks%22%2C%7B%22children%22%3A%5B%22__PAGE__%22%2C%7B%7D%2C%22%2Faccount%2Fpro-perks%22%2C%22refresh%22%5D%7D%5D%7D%2Cnull%2Cnull%2Ctrue%5D%7D%2Cnull%2Cnull%5D%7D%2Cnull%2Cnull%5D%7D%2Cnull%2Cnull%5D%7D%2Cnull%2Cnull%2Ctrue%5D",
                        "next-url": "/account/pro-perks",
                        "priority": "u=1, i",
                        "rsc": "1",
                        "sec-ch-ua": "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"",
                        "sec-ch-ua-arch": "\"x86\"",
                        "sec-ch-ua-bitness": "\"64\"",
                        "sec-ch-ua-full-version": "\"131.0.6753.0\"",
                        "sec-ch-ua-full-version-list": "\"Google Chrome\";v=\"131.0.6753.0\", \"Chromium\";v=\"131.0.6753.0\", \"Not_A Brand\";v=\"24.0.0.0\"",
                        "sec-ch-ua-mobile": "?0",
                        "sec-ch-ua-model": "\"\"",
                        "sec-ch-ua-platform": "\"Windows\"",
                        "sec-ch-ua-platform-version": "\"10.0.0\"",
                        "sec-fetch-dest": "empty",
                        "sec-fetch-mode": "cors",
                        "sec-fetch-site": "same-origin",
                        "cookie": "__cflb=02DiuDyvFMmK5p9jVbVnMNSKYZhUL9aGkfhactzo3W2yA; pplx.visitor-id=1eb6478e-8d01-4104-ae31-4a2f5344239f; pplx.session-id=72680a41-d894-4f19-87c5-3df8801ae78f; next-auth.csrf-token=06b637a7f22ca7d68e1fbb4797734233ed2ec89e23f8a2f91e5d08126d321529%7C89dff67e7e16bd4872f243d35e715e6d51a021fc30660c012ca6f926b74468c1; lat=37.354111; long=-121.95549; sidebarHiddenHubs=[]; pplx.search-mode-layout-variant=enabled; _fbp=fb.1.1747927584367.675995885727796176; pplx.is-incognito=false; next-auth.callback-url=https%3A%2F%2Fwww.perplexity.ai%2Fapi%2Fauth%2Fsignin-callback%3Fredirect%3Dhttps%253A%252F%252Fwww.perplexity.ai%252F%253Flogin-source%253DfileUpload; cf_clearance=0amSaWnTHhFmZHKB7cV4xW0f1jrwKqPYXocLA.WMr4o-1747928308-1.2.1.1-BExIExloHIgipKvGRYWx03RU0jyZ3d4uJNg1_hMijJNQsiEg5y2yivEexCLlhXIsN_tysY0Uicluc1NIz2xveYVUefoV1703mU35_vqjycGrdMw2hHEN7.4CqrJhQuUGN6M0GptDsiCW.qYzN4CiufWA_G_J5BmJ3p9iWUbnOEJF9d1YRANAC5js2tHRTSeHGQ6oEdB.WjEJYD52dOkas0lUb6mo_wNUYShjfYIMIW.C.WypkwqS_kNrYQ_ueBWHu.rgmEBZOhhFYWKGiu0g2LSWL2neWxgMC1NKhrbOG6CXgk29ZDJXCBw4lAY0czDbEL1cokQy_cVi7CYrPFIzjTmHeiqXtSdhidcRctvA0WXsMxZ838XcWwVBOG1ZhS6j; __Secure-next-auth.session-token=eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..4V6OYqVI4PeX7_ho.lOZIa-uxgav9TjCWzkOqzzzVfEpiTSwECMRJQ5MK7sMIFVeQsBT0QjwQcMvJVULJepCWlLTpRJIr-6q72O4cygtjp0NPDrRWBwn66eWasc3r4NdU6Xw8LdiL3bMFzS1mvSuR_5tlW9QmxCjoydov4HwYEa-Dt68DjFdAPy4j3ofTnnV1m8qE-FPeJO-DxW0TL6aiDwEgVhMkS2n-FaUb-PgDTRUsFchsWcJ-1yXb01L-pB3J5sKrLyOJ8Vqa5oO6yg.2CeQAhIWf9GLb20p9ltDUQ; _rdt_uuid=1747927584557.9c628ce3-5c53-4b80-ac83-8d5c2b5a0b19; pplx.metadata={%22qc%22:19%2C%22qcu%22:0%2C%22qcm%22:0%2C%22qcc%22:0%2C%22qcr%22:0%2C%22qcdr%22:0%2C%22qcs%22:0%2C%22qcd%22:0%2C%22hli%22:true%2C%22hcga%22:false%2C%22hcds%22:false%2C%22hso%22:true%2C%22hfo%22:true}; AWSALB=y/u8Q04YmC/ivqG21uBn4tK3zyrz4spSxoiARiBFwfJ4DdnakoNKvQDiUo8jqnByoqgzCRjulLDrG8yTxUS7PCl7CewQlB6LrlXTguow8C95PGG+x+1CZtRmvWMu; AWSALBCORS=y/u8Q04YmC/ivqG21uBn4tK3zyrz4spSxoiARiBFwfJ4DdnakoNKvQDiUo8jqnByoqgzCRjulLDrG8yTxUS7PCl7CewQlB6LrlXTguow8C95PGG+x+1CZtRmvWMu; __cf_bm=i7w_6W0CX.YmZPy2RRR.z8PH3yDu3EI4fAHn7vb9mf8-1747928346-1.0.1.1-WrMA.KKcbNu72D3blDf3rn8HRvhYfUPLqgeeztSw2CFZAaptQKhTREywLEGpwh0bYizstnMLgsSqHTkIvasM9iUx0.9FGrQRo.fcjaqluGM; _dd_s=aid=882237df-c52d-4a62-91e5-7926c1a22304&rum=0&expire=1747929250085&logs=0",
                        "Referer": "https://www.perplexity.ai/account/pro-perks",
                        "Referrer-Policy": "strict-origin-when-cross-origin"
            },
            "method": "GET",
                    "credentials": "include" // 确保包含凭证
            });

            // 获取响应文本
            const responseText = await response.text();

            // 提取referral链接
            const referralLink = extractReferralLink(responseText);

            // 如果找到链接，则输出并停止
            if (referralLink) {
                console.log(`${referralLink}`);
                // 找到链接后不停止，继续尝试获取更多链接
            }
        } catch (error) {
            // 失败时不输出任何内容
        }
    }, 1); // 每1毫秒执行一次
    }

// 将函数暴露到全局作用域，以便用户可以手动调用
window.startFetching = startFetching;
        window.stopFetching = stopFetching;

// 自动开始执行
        startFetching();


