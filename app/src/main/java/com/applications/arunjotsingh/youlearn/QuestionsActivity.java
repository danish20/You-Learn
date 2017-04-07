package com.applications.arunjotsingh.youlearn;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by arunjotsingh on 4/27/16.
 */
public class QuestionsActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    String[] question=
            {
                    "What are chemical substances released into blood that affect target cells in other organs called?",
                    "What are chemical substances released into blood that affect target cells in other organs called?",
                    "What are chemical substances released into blood that affect target cells in other organs called?",
                    "What are chemical substances released into blood that affect target cells in other organs called?",
                    "What are chemical substances released into blood that affect target cells in other organs called?",
                    "What are chemical substances released into blood that affect target cells in other organs called?",
                    "What are chemical substances released into blood that affect target cells in other organs called?"
            };

    String[] option1=
            {
                    "Hormones",
                    "Hormones",
                    "Hormones",
                    "Hormones",
                    "Hormones",
                    "Hormones",
                    "Hormones"

            };
    String[] option2=
            {
                    "Phermones",
                    "Phermones",
                    "Phermones",
                    "Phermones",
                    "Phermones",
                    "Phermones",
                    "Phermones"

            };
    String[] option3=
            {
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters"

            };
    String[] option4=
            {
                    "Peptides",
                    "Peptides",
                    "Peptides",
                    "Peptides",
                    "Peptides",
                    "Peptides",
                    "Peptides"

            };
    String[] ans=
            {
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters",
                    "Neurotransmitters"
            };

    RadioButton r1,r2,r3,r4;
    RadioGroup rg;
    int ques=0;
    int correct,incorrect;
    TextView tv,num,result;
    Boolean flag=true;
    LinearLayout queslay,reslay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionare_layout);
        correct=0;
        incorrect=0;
        queslay=(LinearLayout)findViewById(R.id.queslay);
        reslay=(LinearLayout)findViewById(R.id.resultlay);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        r1=(RadioButton)findViewById(R.id.radio1);
        r2=(RadioButton)findViewById(R.id.radio2);
        r3=(RadioButton)findViewById(R.id.radio3);
        r4=(RadioButton)findViewById(R.id.radio4);
        rg=(RadioGroup)findViewById(R.id.radioGroup);
        tv=(TextView)findViewById(R.id.question);
        result=(TextView)findViewById(R.id.result);
        num=(TextView)findViewById(R.id.quesnum);
        tv.setText(question[ques]);
        num.setText("Question "+(ques+1));
        r1.setText(option1[ques]);
        r2.setText(option2[ques]);
        r3.setText(option3[ques]);
        r4.setText(option4[ques]);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (flag) {
                    int id = radioGroup.getCheckedRadioButtonId();
                    switch (id) {
                        case R.id.radio1:
                            if (option1[ques].equals(ans[ques])) {
                                correct++;
                            }
                            break;

                        case R.id.radio2:
                            if (option2[ques].equals(ans[ques])) {
                                correct++;
                            }
                            break;

                        case R.id.radio3:
                            if (option3[ques].equals(ans[ques])) {
                                correct++;
                            }
                            break;

                        case R.id.radio4:
                            if (option4[ques].equals(ans[ques])) {
                                correct++;
                            }
                            break;
                        default: {
                            incorrect++;
                        }
                    }
                }
                else
                {
                    flag=true;
                }
            }
        });

    }

public void next(View v)
{
    if(ques<question.length-1)
    {
        ques++;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(question[ques]);
                num.setText("Question "+(ques+1));
                r1.setText(option1[ques]);
                r2.setText(option2[ques]);
                r3.setText(option3[ques]);
                r4.setText(option4[ques]);
                flag=false;
                rg.clearCheck();
            }
        });
    }
    else
    {
        result.setText("Correct Answers: "+correct+" and Incorrect Answers: "+(question.length-correct));
        reslay.setVisibility(View.VISIBLE);
        queslay.setVisibility(View.GONE);
    }
}

    public void prev(View v)
    {
        if(ques>0)
        {
            ques--;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv.setText(question[ques]);
                    num.setText("Question " + (ques + 1));
                    r1.setText(option1[ques]);
                    r2.setText(option2[ques]);
                    r3.setText(option3[ques]);
                    r4.setText(option4[ques]);
                    flag = false;
                    rg.clearCheck();
                }
            });
        }

    }

    public void recommend(View v)
    {
        //Recommendation Screen
    }


}