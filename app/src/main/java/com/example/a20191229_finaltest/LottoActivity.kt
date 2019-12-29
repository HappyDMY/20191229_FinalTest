package com.example.a20191229_finaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*

class LottoActivity : BaseActivity() {

    var totalWinMoney = 0L //0을 Long타입으로 대입 => 50억은 Int로 못 담음
    var usedMoney = 0L //0을 Long타입으로 대입 => 50억은 Int로 못 담음
    var boundsNumber = 0 //보너스 번호를 담기위한 변수
    val winLottoNumArr =ArrayList<Int>()
    val winLottoNumTextViewList = ArrayList<TextView>()
    val myLottoNumTextViewList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {
        buyOneLottoBtn.setOnClickListener {
//            6개의 숫자를 랜덤으로 생성 => 텍스트뷰 6 개에 반영
            makeWinLottoNum()
//            몇등인지 판단하기
            checkLottoRank()
        }
    }


    fun checkLottoRank(){
//        등수 판단?
//        내가 가진 숫자들과 / 당첨번호를 하나하나 비교해서, 같은 숫자가 몇개인지 세어야 함.
//        이 갯수게 따리 등수를 판정.

//        같은 숫자를 tpdjwnsms qustn
        var correctCount = 0

        for (myNumTxt in myLottoNumTextViewList){
            val num = myNumTxt.text.toString().toInt()
            Log.d("적혀 있는 숫자들 ", num.toString())

//            당첨번호르 둘러보자
            for (winNum in winLottoNumArr){
//                같은 숫자를 찾았다면
                if (num == winNum){
//                    당첨번호에 들어있다! 갯수 1 증가
                    correctCount++
                    break
                }
            }

        }

//        1등 당첨금 => 50억
//        2등 당첨금 => 150만원
//        3등 당첨금 => 5만원
//        4등 당첨금 => 5천원
//        맞춪 갯수에 따라 등수 판정
        var isSecondRank = false

        if (correctCount == 6 ){
//            1등당첨
            totalWinMoney +=500000000

        }else if (correctCount  == 5){
//            보너스 번호가 맞다면 2등 아니라면 3등
            for(myNumTxt in myLottoNumTextViewList){
                val myNumber = myNumTxt.text.toString().toInt()

                if(myNumber == boundsNumber){
//                    2등 당첨 5000만원
                    isSecondRank = true
                    totalWinMoney +=50000000

                }else{
//                    3등 당첨
                    totalWinMoney +=1500000
                }

            }

        }else if (correctCount ==4){
//            4등당첨
            totalWinMoney +=50000

        }else if (correctCount ==3){
//            3등당첨
            totalWinMoney +=5000

        }else {
//            꽝임

        }

//        당첨금액
//        totalWinMoneyTxt.text = totalWinMoney.toString()
        totalWinMoneyTxt.text = String.format("%,d원",totalWinMoney)

//        사용금액
        usedMoney +=1000
//        usedMoneyTxt.text = usedMoney.toString()
        usedMoneyTxt.text = String.format("%,d원",usedMoney)

    }//fun end
//

    fun makeWinLottoNum(){

//        기존의 당첨번호 클리어
//        6개의 당첨 번호 생성 => 6번 반복
//        랜덤으로 숫자를 생성 => 아무 제약업는 랜덤이 아니라 => 1~45의 범위, / 중복허용 X 제약조건
//        제약조건을 통과한다면 => 당첨번호 목록으로 추가. (2, 10, 25, 48, 8, 17) => 배열(ArrayList) 사용
//        작은 숫자부터 나열 되도록 정렬
//        여기까지 완료되면 6개의 텍스트 뷰에 반영

//        기존 당첨번호 클리어
        winLottoNumArr.clear()
//        기존 보너스번호 클리어
        boundsNumber = 0


//        당첨번호를 6개 만들기 위한 for 문
        for (i in 0..5){
//            제약조건을 만족할 때까지 (몇번 돌아야 맞녹인지 모름.) 무한반복 while
            while (true){
                val randomInt = Random().nextInt(45)+1 //0~44의 랜덤값 +1 1~45사이의 랜덤
                var isDuplOk = true
                for(num in winLottoNumArr){
                    if (randomInt == num){
//                    이미 뽑아둔 번호와 랜덤번호가 같으면 중복검사 통과 X
                        isDuplOk = false
                        break
                    }

                    if(isDuplOk){
                        winLottoNumArr.add(randomInt)
                        break
                    }

                }//for end
            }//while end
        }//for end

        Collections.sort(winLottoNumArr)

//        보너스 번호도 생성
//        1~45의 값, 기존 당첨번호와 중복되지 않아야 함 1개만
        while (true){
            val tempNum = Random().nextInt(45)+1

            var isDuplOk = true

            for (winNum in winLottoNumArr){
                if (tempNum == winNum){
                    isDuplOk = false
                }
            }

            if (isDuplOk){
                boundsNumber = tempNum
                break
            }
        }

        for (num in winLottoNumArr){
            Log.d("당첨번호", num.toString())
        }

        for (i in 0..5) {
//            상황에 맞는 텍스트뷰 / 당첨번호를 뽑아와서
            val tempTextView = winLottoNumTextViewList.get(i)
            val winNum = winLottoNumArr.get(i)

//            값으로 세팅
            tempTextView.text = winNum.toString()
        }

    }

    override fun setValues() {

        winLottoNumTextViewList.add(lottoNumTxt01)
        winLottoNumTextViewList.add(lottoNumTxt02)
        winLottoNumTextViewList.add(lottoNumTxt03)
        winLottoNumTextViewList.add(lottoNumTxt04)
        winLottoNumTextViewList.add(lottoNumTxt05)
        winLottoNumTextViewList.add(lottoNumTxt06)


        myLottoNumTextViewList.add(myNumTxt01)
        myLottoNumTextViewList.add(myNumTxt02)
        myLottoNumTextViewList.add(myNumTxt03)
        myLottoNumTextViewList.add(myNumTxt04)
        myLottoNumTextViewList.add(myNumTxt05)
        myLottoNumTextViewList.add(myNumTxt06)

    }

}
