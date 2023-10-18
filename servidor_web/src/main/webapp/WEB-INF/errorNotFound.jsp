<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error 404</title>
    </head>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:400,100,300,500);

        body {
            background-color: #002333;
            color: #fff;
            font-size: 100%;
            line-height: 1.5;
            font-family: "Roboto", sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .button {
            font-weight: 300;
            color: #fff;
            font-size: 1.2em;
            text-decoration: none;
            border: 1px solid #efefef;
            padding: .5em;
            border-radius: 3px;
            transition: all .3s linear;
        }

        .button:hover {
            background-color: transparent;
            color: #fff;
        }

        p {
            font-size: 2em;
            text-align: center;
            font-weight: 100;
        }

        h1 {
            margin: 0px;
            text-align: center;
            font-size: 15em;
            font-weight: 100;
            text-shadow: #002333 1px 1px, #002333 2px 2px, #002333 3px 3px, #002333 4px 4px, #002333 5px 5px, #002333 6px 6px, #002333 7px 7px, #002333 8px 8px, #002334 9px 9px, #002334 10px 10px, #002334 11px 11px, #002334 12px 12px, #002334 13px 13px, #002334 14px 14px, #002334 15px 15px, #002334 16px 16px, #002334 17px 17px, #002335 18px 18px, #002335 19px 19px, #002335 20px 20px, #002335 21px 21px, #002335 22px 22px, #002335 23px 23px, #002335 24px 24px, #002335 25px 25px, #002335 26px 26px, #002335 27px 27px, #002336 28px 28px, #002336 29px 29px, #002336 30px 30px, #002336 31px 31px, #002336 32px 32px, #002336 33px 33px, #002336 34px 34px, #002336 35px 35px, #002336 36px 36px, #002336 37px 37px, #002337 38px 38px, #002337 39px 39px, #002337 40px 40px, #002337 41px 41px, #002337 42px 42px, #002337 43px 43px, #002337 44px 44px, #002337 45px 45px, #002337 46px 46px, #002338 47px 47px, #002338 48px 48px, #002338 49px 49px, #002338 50px 50px, #002338 51px 51px, #002338 52px 52px, #002338 53px 53px, #002338 54px 54px, #002338 55px 55px, #002339 56px 56px, #002339 57px 57px, #002339 58px 58px, #002339 59px 59px, #002339 60px 60px, #002339 61px 61px, #002339 62px 62px, #002339 63px 63px, #002339 64px 64px, #00233a 65px 65px, #00233a 66px 66px, #00233a 67px 67px, #00233a 68px 68px, #00233a 69px 69px, #00233a 70px 70px, #00233a 71px 71px, #00233a 72px 72px, #00233a 73px 73px, #00233b 74px 74px, #00233b 75px 75px, #00233b 76px 76px, #00233b 77px 77px, #00233b 78px 78px, #00233b 79px 79px, #00233b 80px 80px, #00233b 81px 81px, #00233b 82px 82px, #00233b 83px 83px, #00233c 84px 84px, #00233c 85px 85px, #00233c 86px 86px, #00233c 87px 87px, #00233c 88px 88px, #00233c 89px 89px, #00233c 90px 90px, #00233c 91px 91px, #00233c 92px 92px, #00233d 93px 93px, #00233d 94px 94px, #00233d 95px 95px, #00233d 96px 96px, #00233d 97px 97px, #00233d 98px 98px, #00233d 99px 99px, #00233d 100px 100px, #00233d 101px 101px, #00233e 102px 102px, #00233e 103px 103px, #00233e 104px 104px, #00233e 105px 105px, #00233e 106px 106px, #00233e 107px 107px, #00233e 108px 108px, #00233e 109px 109px, #00233e 110px 110px, #00233f 111px 111px, #00233f 112px 112px, #00233f 113px 113px, #00233f 114px 114px, #00233f 115px 115px, #00233f 116px 116px, #00233f 117px 117px, #00233f 118px 118px, #00233f 119px 119px, #00233f 120px 120px, #00233f 121px 121px, #00233f 122px 122px, #00233f 123px 123px, #00233f 124px 124px, #00233f 125px 125px, #00233f 126px 126px, #00233f 127px 127px, #00233f 128px 128px, #00233f 129px 129px, #00233f 130px 130px, #00233f 131px 131px, #00233f 132px 132px, #00233f 133px 133px, #00233f 134px 134px, #00233f 135px 135px, #00233f 136px 136px, #00233f 137px 137px, #00233f 138px 138px, #00233f 139px 139px, #00233f 140px 140px, #00233f 141px 141px, #00233f 142px 142px, #00233f 143px 143px, #00233f 144px 144px, #00233f 145px 145px, #00233f 146px 146px, #00233f 147px 147px, #00233f 148px 148px, #00233f 149px 149px, #00233f 150px 150px, #00233f 151px 151px, #00233f 152px 152px, #00233f 153px 153px, #00233f 154px 154px, #00233f 155px 155px, #00233f 156px 156px, #00233f 157px 157px, #00233f 158px 158px, #00233f 159px 159px, #00233f 160px 160px, #00233f 161px 161px, #00233f 162px 162px, #00233f 163px 163px, #00233f 164px 164px, #00233f 165px 165px, #00233f 166px 166px, #00233f 167px 167px, #00233f 168px 168px, #00233f 169px 169px, #00233f 170px 170px, #00233f 171px 171px, #00233f 172px 172px, #00233f 173px 173px, #00233f 174px 174px, #00233f 175px 175px, #00233f 176px 176px, #00233f 177px 177px, #00233f 178px 178px, #00233f 179px 179px, #00233f 180px 180px, #00233f 181px 181px, #00233f 182px 182px, #00233f 183px 183px, #00233f 184px 184px, #00233f 185px 185px, #00233f 186px 186px, #00233f 187px 187px, #00233f 188px 188px, #00233f 189px 189px, #00233f 190px 190px, #00233f 191px 191px, #00233f 192px 192px, #00233f 193px 193px, #00233f 194px 194px, #00233f 195px 195px, #00233f 196px 196px, #00233f 197px 197px, #00233f 198px 198px, #00233f 199px 199px, #00233f 200px 200px;
        }
    </style>

    <body>
        <h1>404</h1>
        <p>Oops! No encontramos el recurso que buscas!</p>
        <a class="button" href="./home"><i class="icon-home"></i>Volver al inicio</a>
    </body>

    </html>