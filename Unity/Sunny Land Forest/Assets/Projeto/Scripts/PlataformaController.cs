using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlataformaController : MonoBehaviour
{
    public Transform plataforma, pontoA, pontoB;
    public float velocidadePlataforma;

    private Vector3 pontoDestino;

    // Start is called before the first frame update
    void Start()
    {
        plataforma.position = pontoA.position;

        pontoDestino = pontoB.position;
    }

    // Update is called once per frame
    void Update()
    {
        plataforma.position = Vector3.MoveTowards(plataforma.position, pontoDestino, velocidadePlataforma * Time.deltaTime);

        if (plataforma.position == pontoDestino)
        {
            if (pontoDestino == pontoA.position)
            {
                pontoDestino = pontoB.position;
            }
            else if (pontoDestino == pontoB.position)
            {
                pontoDestino = pontoA.position;
            }
        }
    }
}
