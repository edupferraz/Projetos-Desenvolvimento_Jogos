using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameController : MonoBehaviour
{
    
    private int score;

    [Header("Contagem de Cenouras")]
    public Text txtScore;

    [Header("Audios")]

    public AudioSource fxGame;

    public AudioClip fxCenoura;

    public AudioClip fxPulo;

    public AudioClip fxExplosao;

    public AudioClip fxDie;

    [Header("Prefab Explosao")]

    public GameObject prefabExplosao;

    [Header("Hud Vidas")]

    public Sprite[] imagensVida;

    public Image barraVida;

    public Transform pontoFinal;

    public void Pontuacao(int qtdPontos)
    {
        score = score + qtdPontos;

        txtScore.text = score.ToString();

        //som da coleta da cenoura
        fxGame.PlayOneShot(fxCenoura);
    }

    public void BarraVida (int healthVida)
    {
        barraVida.sprite = imagensVida[healthVida];
    }
}
