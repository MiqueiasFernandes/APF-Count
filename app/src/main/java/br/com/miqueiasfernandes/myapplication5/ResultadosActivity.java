package br.com.miqueiasfernandes.myapplication5;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Miquéias Fernandes on 04/12/2016.
 */

public class ResultadosActivity extends AppCompatActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);
        
       Bundle bundle = getIntent().getExtras();

     if (bundle != null) {

      ((TextView) findViewById(R.id.name)).setText("Resultados da contagem: " + bundle.getString("name", "-"));

         double produtividade = bundle.getDouble("produtividade");
         double custo = bundle.getDouble("custo");

         if(!Double.isNaN(produtividade) || !Double.isNaN(custo)){
             (findViewById(R.id.row_extras)).setVisibility(View.VISIBLE);
             (findViewById(R.id.view_extras)).setVisibility(View.VISIBLE);
             (findViewById(R.id.row_extras_title)).setVisibility(View.VISIBLE);
         }else{
             (findViewById(R.id.row_extras)).setVisibility(View.INVISIBLE);
             ( findViewById(R.id.view_extras)).setVisibility(View.INVISIBLE);
             (findViewById(R.id.row_extras_title)).setVisibility(View.INVISIBLE);
         }


      if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

       ((TextView) findViewById(R.id.indicativa_ali_qtd)).setText(bundle.getString("INDICATIVA_ALI_QTD", "-"));
       ((TextView) findViewById(R.id.indicativa_ali_pf)).setText(bundle.getString("INDICATIVA_ALI_PF", "-"));
       ((TextView) findViewById(R.id.indicativa_aie_qtd)).setText(bundle.getString("INDICATIVA_AIE_QTD", "-"));
       ((TextView) findViewById(R.id.indicativa_aie_pf)).setText(bundle.getString("INDICATIVA_AIE_PF", "-"));
       //((TextView) findViewById(R.id.indicativa_qtd)).setText(  bundle.getString("INDICATIVA_TOTAL_QTD", "-"));
       ((TextView) findViewById(R.id.indicativa_pf)).setText(bundle.getString("INDICATIVA_TOTAL", "-") + " PFB");

       ((TextView) findViewById(R.id.estimativa_ali_pf)).setText(bundle.getString("ESTIMADA_TOTAL_ALI", "-"));
       ((TextView) findViewById(R.id.estimativa_aie_pf)).setText(bundle.getString("ESTIMADA_TOTAL_AIE", "-"));
       ((TextView) findViewById(R.id.estimativa_ee_pf)).setText(bundle.getString("ESTIMADA_TOTAL_EE", "-"));
       ((TextView) findViewById(R.id.estimativa_ce_pf)).setText(bundle.getString("ESTIMADA_TOTAL_CE", "-"));
       ((TextView) findViewById(R.id.estimativa_se_pf)).setText(bundle.getString("ESTIMADA_TOTAL_SE", "-"));
       ((TextView) findViewById(R.id.estimativa_pf)).setText(bundle.getString("ESTIMADA_TOTAL", "-") + " PFB");

       ((TextView) findViewById(R.id.detalhada_ali_pf)).setText(bundle.getString("DETALHADA_TOTAL_ALI", "-"));
       ((TextView) findViewById(R.id.detalhada_aie_pf)).setText(bundle.getString("DETALHADA_TOTAL_AIE", "-"));
       ((TextView) findViewById(R.id.detalhada_ee_pf)).setText(bundle.getString("DETALHADA_TOTAL_EE", "-"));
       ((TextView) findViewById(R.id.detalhada_ce_pf)).setText(bundle.getString("DETALHADA_TOTAL_CE", "-"));
       ((TextView) findViewById(R.id.detalhada_se_pf)).setText(bundle.getString("DETALHADA_TOTAL_SE", "-"));
       ((TextView) findViewById(R.id.detalhada_pf)).setText(bundle.getString("DETALHADA_TOTAL", "-") + " PFB");

       ((TextView) findViewById(R.id.estimativa_ali_cb)).setText(bundle.getString("ESTIMADA_ALI_CB", "-"));
       ((TextView) findViewById(R.id.estimativa_ali_cm)).setText(bundle.getString("ESTIMADA_ALI_CM", "-"));
       ((TextView) findViewById(R.id.estimativa_ali_ca)).setText(bundle.getString("ESTIMADA_ALI_CA", "-"));
       ((TextView) findViewById(R.id.estimativa_aie_cb)).setText(bundle.getString("ESTIMADA_AIE_CB", "-"));
       ((TextView) findViewById(R.id.estimativa_aie_cm)).setText(bundle.getString("ESTIMADA_AIE_CM", "-"));
       ((TextView) findViewById(R.id.estimativa_aie_ca)).setText(bundle.getString("ESTIMADA_AIE_CA", "-"));
       ((TextView) findViewById(R.id.estimativa_ee_cb)).setText(bundle.getString("ESTIMADA_EE_CB", "-"));
       ((TextView) findViewById(R.id.estimativa_ee_cm)).setText(bundle.getString("ESTIMADA_EE_CM", "-"));
       ((TextView) findViewById(R.id.estimativa_ee_ca)).setText(bundle.getString("ESTIMADA_EE_CA", "-"));
       ((TextView) findViewById(R.id.estimativa_ce_cb)).setText(bundle.getString("ESTIMADA_CE_CB", "-"));
       ((TextView) findViewById(R.id.estimativa_ce_cm)).setText(bundle.getString("ESTIMADA_CE_CM", "-"));
       ((TextView) findViewById(R.id.estimativa_ce_ca)).setText(bundle.getString("ESTIMADA_CE_CA", "-"));
       ((TextView) findViewById(R.id.estimativa_se_cb)).setText(bundle.getString("ESTIMADA_SE_CB", "-"));
       ((TextView) findViewById(R.id.estimativa_se_cm)).setText(bundle.getString("ESTIMADA_SE_CM", "-"));
       ((TextView) findViewById(R.id.estimativa_se_ca)).setText(bundle.getString("ESTIMADA_SE_CA", "-"));

       ((TextView) findViewById(R.id.detalhada_ali_cb)).setText(bundle.getString("DETALHADA_ALI_CB", "-"));
       ((TextView) findViewById(R.id.detalhada_ali_cm)).setText(bundle.getString("DETALHADA_ALI_CM", "-"));
       ((TextView) findViewById(R.id.detalhada_ali_ca)).setText(bundle.getString("DETALHADA_ALI_CA", "-"));
       ((TextView) findViewById(R.id.detalhada_aie_cb)).setText(bundle.getString("DETALHADA_AIE_CB", "-"));
       ((TextView) findViewById(R.id.detalhada_aie_cm)).setText(bundle.getString("DETALHADA_AIE_CM", "-"));
       ((TextView) findViewById(R.id.detalhada_aie_ca)).setText(bundle.getString("DETALHADA_AIE_CA", "-"));
       ((TextView) findViewById(R.id.detalhada_ee_cb)).setText(bundle.getString("DETALHADA_EE_CB", "-"));
       ((TextView) findViewById(R.id.detalhada_ee_cm)).setText(bundle.getString("DETALHADA_EE_CM", "-"));
       ((TextView) findViewById(R.id.detalhada_ee_ca)).setText(bundle.getString("DETALHADA_EE_CA", "-"));
       ((TextView) findViewById(R.id.detalhada_ce_cb)).setText(bundle.getString("DETALHADA_CE_CB", "-"));
       ((TextView) findViewById(R.id.detalhada_ce_cm)).setText(bundle.getString("DETALHADA_CE_CM", "-"));
       ((TextView) findViewById(R.id.detalhada_ce_ca)).setText(bundle.getString("DETALHADA_CE_CA", "-"));
       ((TextView) findViewById(R.id.detalhada_se_cb)).setText(bundle.getString("DETALHADA_SE_CB", "-"));
       ((TextView) findViewById(R.id.detalhada_se_cm)).setText(bundle.getString("DETALHADA_SE_CM", "-"));
       ((TextView) findViewById(R.id.detalhada_se_ca)).setText(bundle.getString("DETALHADA_SE_CA", "-"));

          if(!Double.isNaN(produtividade) || !Double.isNaN(custo)){
              ((TextView) findViewById(R.id.extras_base_pf)).setText("BASE " + bundle.getString("DETALHADA_TOTAL", "-") + " PF");
          }

          if(!Double.isNaN(produtividade)){
              ((TextView) findViewById(R.id.esforco_base)).setText( produtividade + " Hr/PF");
              ((TextView) findViewById(R.id.esforco_valor)).setText( bundle.getDouble("esforco_detalhada") + " Hrs");
              (findViewById(R.id.row_extras_esforco)).setVisibility(View.VISIBLE);
          }else
          {
              (findViewById(R.id.row_extras_esforco)).setVisibility(View.INVISIBLE);
          }

          if(!Double.isNaN(custo)){
              ((TextView) findViewById(R.id.custo_base)).setText("$ " + custo + "/PF");
              ((TextView) findViewById(R.id.custo_valor)).setText("$ " + bundle.getDouble("custo_detalhada"));
              (findViewById(R.id.row_extras_custo)).setVisibility(View.VISIBLE);
          }
          else{
              (findViewById(R.id.row_extras_esforco)).setVisibility(View.INVISIBLE);
          }


      }else{

       ((TextView) findViewById(R.id.indicativa_ali_pf)).setText(bundle.getString("INDICATIVA_ALI_PF", "-"));
       ((TextView) findViewById(R.id.indicativa_aie_pf)).setText(bundle.getString("INDICATIVA_AIE_PF", "-"));
       ((TextView) findViewById(R.id.indicativa_ee_pf)).setText("-");
       ((TextView) findViewById(R.id.indicativa_ce_pf)).setText("-");
       ((TextView) findViewById(R.id.indicativa_se_pf)).setText("-");
       ((TextView) findViewById(R.id.indicativa_total_pf)).setText(bundle.getString("INDICATIVA_TOTAL", "-"));

       ((TextView) findViewById(R.id.estimativa_ali_pf)).setText(bundle.getString("ESTIMADA_TOTAL_ALI", "-"));
       ((TextView) findViewById(R.id.estimativa_aie_pf)).setText(bundle.getString("ESTIMADA_TOTAL_AIE", "-"));
       ((TextView) findViewById(R.id.estimativa_ee_pf)).setText(bundle.getString("ESTIMADA_TOTAL_EE", "-"));
       ((TextView) findViewById(R.id.estimativa_ce_pf)).setText(bundle.getString("ESTIMADA_TOTAL_CE", "-"));
       ((TextView) findViewById(R.id.estimativa_se_pf)).setText(bundle.getString("ESTIMADA_TOTAL_SE", "-"));
       ((TextView) findViewById(R.id.estimativa_total_pf)).setText(bundle.getString("ESTIMADA_TOTAL", "-"));
       
       ((TextView) findViewById(R.id.detalhada_ali_pf)).setText(bundle.getString("DETALHADA_TOTAL_ALI", "-"));
       ((TextView) findViewById(R.id.detalhada_aie_pf)).setText(bundle.getString("DETALHADA_TOTAL_AIE", "-"));
       ((TextView) findViewById(R.id.detalhada_ee_pf)).setText(bundle.getString("DETALHADA_TOTAL_EE", "-"));
       ((TextView) findViewById(R.id.detalhada_ce_pf)).setText(bundle.getString("DETALHADA_TOTAL_CE", "-"));
       ((TextView) findViewById(R.id.detalhada_se_pf)).setText(bundle.getString("DETALHADA_TOTAL_SE", "-"));
       ((TextView) findViewById(R.id.detalhada_total_pf)).setText(bundle.getString("DETALHADA_TOTAL", "-"));

          if(!Double.isNaN(produtividade)){
              ((TextView) findViewById(R.id.esforco_indicativa)).setText(bundle.getDouble("esforco_indicativa") + " Hrs");
              ((TextView) findViewById(R.id.esforco_estimativa)).setText(bundle.getDouble("esforco_estimativa") + " Hrs");
              ((TextView) findViewById(R.id.esforco_detalhada)).setText(bundle.getDouble("esforco_detalhada") + " Hrs");
              (findViewById(R.id.row_extras_esforco)).setVisibility(View.VISIBLE);
          }
          else{
              (findViewById(R.id.row_extras_esforco)).setVisibility(View.INVISIBLE);
          }

          if(!Double.isNaN(custo)){
              ((TextView) findViewById(R.id.custo_estimativa)).setText("$ " + bundle.getDouble("custo_indicativa"));
              ((TextView) findViewById(R.id.custo_indicativa)).setText("$ " + bundle.getDouble("custo_estimativa"));
              ((TextView) findViewById(R.id.custo_detalhada)).setText("$ " + bundle.getDouble("custo_detalhada"));
              (findViewById(R.id.row_extras_custo)).setVisibility(View.VISIBLE);
          }
          else{
              (findViewById(R.id.row_extras_custo)).setVisibility(View.INVISIBLE);
          }
      }
     }
    }



}
