package com.loma;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

// Observable.range(2,5).subscribe(new Action1<Integer>() {
//@Override
//public void call(Integer integer) {
//        Log.d("JG",integer.toString());// 2,3,4,5,6 从2开始发射5个数据
//        }
//        });
class RxTest{
    public  void flatMapTest(){


              Observable.just(
                    1,
                    2,
                    3)

                    .flatMap(new Function<Integer, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(Integer s) throws Exception {
                            return Observable.just(s+"");

                        }
                    })
                    .subscribe(r -> {});




    }
}

