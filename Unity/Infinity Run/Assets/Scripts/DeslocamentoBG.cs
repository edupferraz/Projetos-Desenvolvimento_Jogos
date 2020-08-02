using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeslocamentoBG : MonoBehaviour
{
    private Renderer objetoRender;
    private Material objetoMaterial;

    public float offset;
    public float offsetIncremento;
    public float offsetVelocidade;


    public string sortingLayer;
    public int orderInLayer;
    void Start()
    {
        objetoRender = GetComponent<MeshRenderer>();


        objetoMaterial = objetoRender.material;

        objetoRender.sortingLayerName = sortingLayer;

        objetoRender.sortingOrder = orderInLayer;
    }

    // Update is called once per frame
    void Update()
    {
        offset = +offsetIncremento;
        objetoMaterial.SetTextureOffset("_MainTex", new Vector2(offset * offsetVelocidade, 0));
    }
}
