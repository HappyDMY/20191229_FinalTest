package com.example.a20191229_finaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*

class LottoActivity : BaseActivity() {

    val winLottoNumArr =ArrayList<Int>()

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
        }
    }

    fun makeWinLottoNum(){

//        기존의 당첨번호 클리어
//        6개의 당첨 번호 생성 => 6번 반복
//        랜덤으로 숫자를 생성 => 아무 제약업는 랜덤이 아니라 => 1~45의 범위, / 중복허용 X 제약조건
//        제약조건을 통과한다면 => 당첨번호 목록으로 추가. (2, 10, 25, 48, 8, 17) => 배열(ArrayList) 사용
//        작은 숫자부터 나열 되도록 정렬
//        여기까지 완료되면 6개의 텍스트 뷰에 반영

        winLottoNumArr.clear()

        for (i in 0..5){
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

        for (num in winLottoNumArr){
            Log.d("당첨번호", num.toString())
        }


    }

    override fun setValues() {
    }
}
