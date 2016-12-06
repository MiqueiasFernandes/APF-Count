package br.com.miqueiasfernandes.myapplication5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.github.fge.jsonschema.util.JsonLoader;

import io.fabric.sdk.android.Fabric;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static br.com.miqueiasfernandes.myapplication5.MainActivity.COMPLEXIDADE.ALTA;
import static br.com.miqueiasfernandes.myapplication5.MainActivity.COMPLEXIDADE.BAIXA;
import static br.com.miqueiasfernandes.myapplication5.MainActivity.COMPLEXIDADE.MEDIA;


public class MainActivity extends AppCompatActivity {

    final String sample =

                    "{\n" +
                            "\"name\": \"Sample APF\",\n" +
                            "\"produtividade\": 1.2,\n" +
                            "\"custo\": 100.10,\n" +
                            "\n" +
                            "\"ALI\" : [{ \"name\" : \"informacoes do produto\", \"tr\" : 3, \"td\" : 9}, { \"name\" : \"informacoes do cliente\", \"tr\" : 8, \"td\" : 16}],\n" +
                            "\"AIE\" : [{ \"name\" : \"informacoes do local\", \"tr\" : 1, \"td\" : 4}],\n" +
                            "\n" +
                            "\"EE\" : [{ \"name\" : \"inserir informacoes do produto\", \"ar\" : 40, \"td\" : 10}],\n" +
                            "\"CE\" : [{ \"name\" : \"consultar informacoes do produto\", \"ar\" : 3, \"td\" : 5}],\n" +
                            "\"SE\" : [{ \"name\" : \"obter estatisticas do produto\", \"ar\" : 5, \"td\" : 42}]\n" +
                            "\n" +
                            "}";
final String pattern =
        "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "  \"title\": \"APF set\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"name\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"ALI\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"name\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"tr\": {\n" +
                "          \"type\": \"number\"\n" +
                "        },\n" +
                "        \"td\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 0\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"AIE\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"name\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"tr\": {\n" +
                "          \"type\": \"number\"\n" +
                "        },\n" +
                "        \"td\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 0\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"EE\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"name\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"ar\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 1\n" +
                "        },\n" +
                "        \"td\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 1\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"CE\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"name\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"ar\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 1\n" +
                "        },\n" +
                "        \"td\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 1\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"SE\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"name\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"ar\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 1\n" +
                "        },\n" +
                "        \"td\": {\n" +
                "          \"type\": \"number\",\n" +
                "          \"minimum\": 1\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("APFcount", MODE_PRIVATE);
        urls = sharedPref.getStringSet("urls", new TreeSet<String>());
        projetos = sharedPref.getStringSet("projetos", new TreeSet<String>());

        setJson(sample);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computar();
            }
        });
        ((Button) findViewById(R.id.btnin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importar();
            }
        });

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.INVISIBLE);
        findViewById(R.id.lblprocess).setVisibility(View.INVISIBLE);

        atualizaUrls();
        atualizaProjetos();

        ((Spinner)
                findViewById(R.id.recentes)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    String projeto = (String) adapterView.getItemAtPosition(i);
                    projeto = projeto.substring(projeto.indexOf(":") + 1);
                    new OpenWorkTask().execute(projeto);
                }catch (Exception ex){

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public String getURL() {
        return ((AutoCompleteTextView) findViewById(R.id.txtURL)).getText().toString();
    }

    public void atualizaUrls(){
        if(urls != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, urls.toArray(new String[]{}));
            AutoCompleteTextView textView = (AutoCompleteTextView)
                    findViewById(R.id.txtURL);
            textView.setAdapter(adapter);
        }
    }

    public void atualizaProjetos(){
        if(projetos != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, projetos.toArray(new String[]{}));
            Spinner textView = (Spinner)
                    findViewById(R.id.recentes);
            textView.setAdapter(adapter);
        }
    }

   private SharedPreferences sharedPref;
    private Set<String> urls;
    private Set<String> projetos;

    private void importar() {
       new OpenWorkTask().execute(new String[]{getURL()});
        urls.add(getURL());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet("urls", urls);
        editor.commit();
        atualizaUrls();
    }

    public void computar() {

        setStatus("obtendo Json", 30);
        String jsonStr = ((EditText) findViewById(R.id.txtJson)).getText().toString();

        JSONObject apfObjects = null;
        try {

            setStatus("validando entrada", 50);

            if (!validate(getJsonNode(jsonStr))) {
                informarErro(new Exception("json invalido"), "json invalido");
                return;
            }

            setStatus("importando objetos", 70);

            apfObjects = new JSONObject(jsonStr);

            String name = apfObjects.optString("name", "-");
            double produtividade = apfObjects.optDouble("produtividade");
            double custo = apfObjects.optDouble("custo");

            setStatus("importando ALI", 74);
            ArrayList<Object[]> ali = getObjects(apfObjects.getJSONArray("ALI"), new String[]{"tr", "td"});

            setStatus("importando AIE", 78);
            ArrayList<Object[]> aie = getObjects(apfObjects.getJSONArray("AIE"), new String[]{"tr", "td"});

            setStatus("importando EE", 82);
            ArrayList<Object[]> ee = getObjects(apfObjects.getJSONArray("EE"), new String[]{"ar", "td"});

            setStatus("importando CE", 86);
            ArrayList<Object[]> ce = getObjects(apfObjects.getJSONArray("CE"), new String[]{"ar", "td"});

            setStatus("importando SE", 90);
            ArrayList<Object[]> se = getObjects(apfObjects.getJSONArray("SE"), new String[]{"ar", "td"});

           setStatus("computando", 95);

            ArrayList<String[]> resi = calcularIndicativa(ali, aie);
            ArrayList<String[]> rese = calcularEstimada(ali, aie, ee, ce, se);
            ArrayList<String[]> resd = calcularDetalhado(ali, aie, ee, ce, se);

            int pfI =  Integer.valueOf(resi.get(resi.size() - 1)[1]);
            int pfE =  Integer.valueOf(rese.get(rese.size() - 1)[1]);
            int pfD =  Integer.valueOf(resd.get(resd.size() - 1)[1]);

            Intent intent = new Intent(this, ResultadosActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("name", name);
            
            if(!Double.isNaN(produtividade)){
                bundle.putDouble("produtividade", produtividade);
                double  esforco_indicativa = getEsforco(produtividade, pfI);
                double esforco_estimativa = getEsforco(produtividade, pfE);
                double  esforco_detalhada = getEsforco(produtividade, pfD);
                bundle.putDouble("esforco_indicativa", esforco_indicativa);
                bundle.putDouble("esforco_estimativa", esforco_estimativa);
                bundle.putDouble("esforco_detalhada", esforco_detalhada);
            }

            if(!Double.isNaN(custo)){
                bundle.putDouble("custo", custo);
                double  custo_indicativa = getCusto(custo, pfI);
                double custo_estimativa = getCusto(custo, pfE);
                double  custo_detalhada = getCusto(custo, pfD);
                bundle.putDouble("custo_indicativa", custo_indicativa);
                bundle.putDouble("custo_estimativa", custo_estimativa);
                bundle.putDouble("custo_detalhada", custo_detalhada);
            }


            for (String[] strings : resi){
                if(strings.length == 2) {
                    bundle.putString(strings[0], strings[1]);
                }
            }
            for (String[] strings : rese){
                if(strings.length == 2) {
                    bundle.putString(strings[0], strings[1]);
                }
            }
            for (String[] strings : resd){
                if(strings.length == 2) {
                    bundle.putString(strings[0], strings[1]);
                }
            }

            intent.putExtras(bundle);
            setStatus("Exibindo resultados", 99);

            startActivity(intent);

        } catch (JSONException e) {
            informarErro(e, "JSONException");
        } catch (IOException e) {
            informarErro(e, "IOException");
        } catch (Exception e) {
            informarErro(e, "Exception");
        }
    }

    private double getEsforco(double produtividade, int pf) {
        return produtividade * pf;
    }
    private double getCusto(double custo, int pf) {
        return custo * pf;
    }

    static enum COMPLEXIDADE { BAIXA, MEDIA, ALTA};

    COMPLEXIDADE getComplexidadeALIAIE(int tr, int td){
        COMPLEXIDADE complexidade;
        if (tr == 1){
            if (td > 50){
                return MEDIA;
            }else{
                return BAIXA;
            }
        } else if (tr > 5){
            if (td < 20){
                return MEDIA;
            }else{
                return ALTA;
            }
        }else{
            if (td < 20){
                return BAIXA;
            }else if (td > 50){
                return ALTA;
            }else{
                return MEDIA;
            }
        }
    }
    COMPLEXIDADE getComplexidadeEE(int ar, int td){
        if (ar < 2){
            if (td > 15){
                return MEDIA;
            }else{
                return BAIXA;
            }
        }else if (ar == 2){
            if (td < 5){
                return BAIXA;
            }else if (td > 15){
                return ALTA;
            }else{
                return MEDIA;
            }
        }else{
            if (td < 5)
            {
                return MEDIA;
            }else{
                return ALTA;
            }
        }
    }
    COMPLEXIDADE getComplexidadeSECE(int ar, int td){
        if (td < 6){
            if (ar > 3){
                return MEDIA;
            }
            else{
                return BAIXA;
            }
        }
        else if (td > 19){
            if (ar < 2){
                return MEDIA;
            } else{
                return ALTA;
            }
        }
        else{
            if (ar < 2){
                return BAIXA;
            }else if (ar > 3){
                return ALTA;
            }
            else{
                return MEDIA;
            }
        }
    }

    int getContribuicaoALI(COMPLEXIDADE complexidade){
        switch (complexidade){
            case BAIXA:
                return 7;
            case MEDIA:
                return 10;
            case ALTA:
                return 15;
            default:
                return 0;
        }
    }
    int getContribuicaoAIE(COMPLEXIDADE complexidade){
        switch (complexidade){
            case BAIXA:
                return 5;
            case MEDIA:
                return 7;
            case ALTA:
                return 10;
            default:
                return 0;
        }
    }
    int getContribuicaoEE(COMPLEXIDADE complexidade){
        switch (complexidade){
            case BAIXA:
                return 3;
            case MEDIA:
                return 4;
            case ALTA:
                return 6;
            default:
                return 0;
        }
    }
    int getContribuicaoSE(COMPLEXIDADE complexidade){
        switch (complexidade){
            case BAIXA:
                return 4;
            case MEDIA:
                return 5;
            case ALTA:
                return 7;
            default:
                return 0;
        }
    }
    int getContribuicaoCE(COMPLEXIDADE complexidade){
        switch (complexidade){
            case BAIXA:
                return 3;
            case MEDIA:
                return 4;
            case ALTA:
                return 6;
            default:
                return 0;
        }
    }

    String getStringFromComplexidade(COMPLEXIDADE complexidade){
        switch (complexidade){
            case BAIXA:
                return "BAIXA";
            case MEDIA:
                return "MEDIA";
            case ALTA:
                return "ALTA";
        }
        return null;
    }

    private ArrayList<String[]> calcularIndicativa(ArrayList<Object[]> ali, ArrayList<Object[]> aie) {
//ALI x 35 e de AIE x 15 -> indicativa
        ArrayList<String[]> linhas = new ArrayList<>();
int alis = ali.size(), aies = aie.size(), pfali = alis * 35, pfaie = aies   * 15;
        linhas.add(new String[] {"INDICATIVA_ALI_QTD", Integer.toString (alis)} );
        linhas.add( new String[] {"INDICATIVA_ALI_PF", Integer.toString(pfali)} );
        linhas.add( new String[] {"INDICATIVA_AIE_QTD", Integer.toString(aies)} );
        linhas.add( new String[] {"INDICATIVA_AIE_PF", Integer.toString(pfaie)} );
        linhas.add(new String[] {"INDICATIVA_TOTAL_QTD", Integer.toString(alis + aies)});
        linhas.add(new String[] {"INDICATIVA_TOTAL",  Integer.toString(pfali + pfaie)});
        return linhas;
    }
    private ArrayList<String[]> calcularEstimada(ArrayList<Object[]> ali, ArrayList<Object[]> aie, ArrayList<Object[]> ee, ArrayList<Object[]> ce, ArrayList<Object[]>se) {
        ArrayList<String[]> linhas = new ArrayList<>();
        int tr,td, ar, count_cb = 0, count_cm = 0, count_ca = 0, pfALI=0, pfAIE=0, pfEE=0, pfCE=0, pfSE=0,total =0, pf;
        String nome;
        COMPLEXIDADE complexidade = null;
        for (Object[] objects : ali){
            nome = (String) objects[0];
            tr = (int) objects[1];
            td = (int) objects[2];
            complexidade = BAIXA;
            pf = getContribuicaoALI(complexidade);
            pfALI += pf;
            total += pf;
            linhas.add(
                    new String[] {"ALI", nome, Integer.toString(tr), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"ESTIMADA_ALI_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"ESTIMADA_ALI_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"ESTIMADA_ALI_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : aie){
            nome = (String) objects[0];
            tr = (int) objects[1];
            td = (int) objects[2];
            complexidade = BAIXA;
            pf = getContribuicaoAIE(complexidade);
            pfAIE += pf;
            total += pf;
            linhas.add(
                    new String[] {"AIE", nome, Integer.toString(tr), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"ESTIMADA_AIE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"ESTIMADA_AIE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"ESTIMADA_AIE_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : ee){
            nome = (String) objects[0];
            ar = (int) objects[1];
            td = (int) objects[2];
            complexidade = MEDIA;
            pf = getContribuicaoEE(complexidade);
            pfEE += pf;
            total += pf;
            linhas.add(
                    new String[] {"EE", nome, Integer.toString(ar), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"ESTIMADA_EE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"ESTIMADA_EE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"ESTIMADA_EE_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : ce){
            nome = (String) objects[0];
            ar = (int) objects[1];
            td = (int) objects[2];
            complexidade = MEDIA;
            pf = getContribuicaoCE(complexidade);
            pfCE += pf;
            total += pf;
            linhas.add(
                    new String[] {"CE", nome, Integer.toString(ar), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"ESTIMADA_CE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"ESTIMADA_CE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"ESTIMADA_CE_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : se){
            nome = (String) objects[0];
            ar = (int) objects[1];
            td = (int) objects[2];
            complexidade = MEDIA;
            pf = getContribuicaoSE(complexidade);
            pfSE += pf;
            total += pf;
            linhas.add(
                    new String[] {"SE", nome, Integer.toString(ar), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"ESTIMADA_SE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"ESTIMADA_SE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"ESTIMADA_SE_CA",  Integer.toString(count_ca)});
        
        linhas.add(new String[] {"ESTIMADA_TOTAL_ALI", Integer.toString(pfALI)});
        linhas.add(new String[] {"ESTIMADA_TOTAL_AIE", Integer.toString(pfAIE)});
        linhas.add(new String[] {"ESTIMADA_TOTAL_EE", Integer.toString(pfEE)});
        linhas.add(new String[] {"ESTIMADA_TOTAL_CE", Integer.toString(pfCE)});
        linhas.add(new String[] {"ESTIMADA_TOTAL_SE", Integer.toString(pfSE)});
        linhas.add(new String[] {"ESTIMADA_TOTAL", Integer.toString(total)});
        return linhas;
    }
    private ArrayList<String[]> calcularDetalhado(ArrayList<Object[]> ali, ArrayList<Object[]> aie, ArrayList<Object[]> ee, ArrayList<Object[]> ce, ArrayList<Object[]>se) {
        ArrayList<String[]> linhas = new ArrayList<>();
        int tr,td, ar, count_cb = 0, count_cm = 0, count_ca = 0,  pfALI=0, pfAIE=0, pfEE=0, pfCE=0, pfSE=0,total =0, pf;
        String nome;
        COMPLEXIDADE complexidade = null;
        for (Object[] objects : ali){
            nome = (String) objects[0];
            tr = (int) objects[1];
            td = (int) objects[2];
            complexidade = getComplexidadeALIAIE(tr,td);
            pf = getContribuicaoALI(complexidade);
            pfALI += pf;
            total += pf;
            linhas.add(
                    new String[] {"ALI", nome, Integer.toString(tr), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"DETALHADA_ALI_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"DETALHADA_ALI_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"DETALHADA_ALI_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : aie){
            nome = (String) objects[0];
            tr = (int) objects[1];
            td = (int) objects[2];
            complexidade = getComplexidadeALIAIE(tr,td);
            pf = getContribuicaoAIE(complexidade);
            pfAIE += pf;
            total += pf;
            linhas.add(
                    new String[] {"AIE", nome, Integer.toString(tr), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"DETALHADA_AIE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"DETALHADA_AIE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"DETALHADA_AIE_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : ee){
            nome = (String) objects[0];
            ar = (int) objects[1];
            td = (int) objects[2];
            complexidade = getComplexidadeEE(ar,td);
            pf = getContribuicaoEE(complexidade);
            pfEE += pf;
            total += pf;
            linhas.add(
                    new String[] {"EE", nome, Integer.toString(ar), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"DETALHADA_EE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"DETALHADA_EE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"DETALHADA_EE_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : ce){
            nome = (String) objects[0];
            ar = (int) objects[1];
            td = (int) objects[2];
            complexidade = getComplexidadeSECE(ar,td);
            pf = getContribuicaoCE(complexidade);
            pfCE += pf;
            total += pf;
            linhas.add(
                    new String[] {"CE", nome, Integer.toString(ar), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"DETALHADA_CE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"DETALHADA_CE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"DETALHADA_CE_CA",  Integer.toString(count_ca)});
        tr=0;td=0;ar=0;count_cb = 0; count_cm = 0; count_ca = 0;
        
        for (Object[] objects : se){
            nome = (String) objects[0];
            ar = (int) objects[1];
            td = (int) objects[2];
            complexidade = getComplexidadeSECE(ar,td);
            pf = getContribuicaoSE(complexidade);
            pfSE += pf;
            total += pf;
            linhas.add(
                    new String[] {"SE", nome, Integer.toString(ar), Integer.toString(td), getStringFromComplexidade(complexidade), Integer.toString(pf)}
            );
            switch (complexidade){
                case BAIXA:
                    count_cb++;
                    break;
                case MEDIA:
                    count_cm++;
                    break;
                case ALTA:
                    count_cm++;
                    break;
            }
        }

        linhas.add(new String[] {"DETALHADA_SE_CB",  Integer.toString(count_cb)});
        linhas.add(new String[] {"DETALHADA_SE_CM",  Integer.toString(count_cm)});
        linhas.add(new String[] {"DETALHADA_SE_CA",  Integer.toString(count_ca)});
        
        linhas.add(new String[] {"DETALHADA_TOTAL_ALI", Integer.toString(pfALI)});
        linhas.add(new String[] {"DETALHADA_TOTAL_AIE", Integer.toString(pfAIE)});
        linhas.add(new String[] {"DETALHADA_TOTAL_EE", Integer.toString(pfEE)});
        linhas.add(new String[] {"DETALHADA_TOTAL_CE", Integer.toString(pfCE)});
        linhas.add(new String[] {"DETALHADA_TOTAL_SE", Integer.toString(pfSE)});
        linhas.add(new String[] {"DETALHADA_TOTAL", Integer.toString(total)});
        return linhas;
    }
    
    public ArrayList<Object[]> getObjects(JSONArray array, String[] fields) throws JSONException {
        ArrayList<Object[]> objects = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject f = array.getJSONObject(i);
            String name = f.optString("name", "");
            int artr = f.getInt(fields[0]);
            int td = f.getInt(fields[1]);
            objects.add(new Object[] {name, artr, td});
        }
        return objects;
    }

    public void setStatus(String text, int stat) {
        TextView lblstatus = ((TextView) findViewById(R.id.lblprocess));
        ProgressBar prog = ((ProgressBar) findViewById(R.id.progressBar));
        lblstatus.setText(text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            prog.setProgress(stat, true);
        } else
            prog.setProgress(stat);
        lblstatus.setVisibility(View.VISIBLE);
        prog.setVisibility(View.VISIBLE);
    }

    void informarErro(Exception erro, String detalhes) {
        Toast.makeText(getApplicationContext(),
                "Houve um erro: " + detalhes + ". Detalhes: " + erro,
                Toast.LENGTH_LONG).show();
    }

    public JsonNode getJsonNode(String string) throws IOException {
        return JsonLoader.fromString(string);
    }

    public JsonNode getJsonNode(URL url) throws IOException {
        return JsonLoader.fromURL(url);
    }

    public boolean validate(JsonNode jsonNode) throws Exception {
        final JsonNode fstabSchema = JsonLoader.fromString(pattern);
        JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(fstabSchema);
        ProcessingReport report = schema.validate(jsonNode);
        return report.isSuccess();
    }

    public void setJson(String json) {

try {
    String url = getURL();
    if(url != null && !url.isEmpty()) {
        String name = new JSONObject(json).optString("name", "-");
        projetos.add(name + ":" + getURL());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet("projetos", projetos);
        editor.commit();
       atualizaProjetos();
    }
}catch (Exception ex){
    informarErro(ex, "armazenar informações de projeto");
}
        ((EditText) findViewById(R.id.txtJson)).setText(json);
    }
    
    class OpenWorkTask extends AsyncTask<String, Void, String> {
        private Exception exception = null;
        @Override
        protected String doInBackground(String... urls) {
            try {
                try {
                   return (JsonLoader.fromURL(new URL(urls[0])).toString());
                } catch (IOException e) {
                    exception = e;
                   return (fromWeb(urls[0]));
                }
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(String message) {
            try {
                setJson(message);
                if(exception != null){
                    informarErro(exception, "erro ao importar dados remoto");
                }
            } catch (Exception e) {
                informarErro(e, "enviando mensgem json");
            }
        }

        public String fromWeb(String endereco){
        try {
            URL url = new URL(endereco);
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader in = new BufferedReader(inputStreamReader);
            String str;
            StringBuilder texto = new StringBuilder();
            while ((str = in.readLine()) != null) {
                texto.append(str + "\n");
            }
            in.close();
            return texto.toString();
        } catch (MalformedURLException e) {
            exception = e;
        } catch (IOException e) {
            exception = e;
        }
        return null;
    }
    }
}