using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RepeatGround : MonoBehaviour
{
    private GameController _gameController;

    public bool _chaoInstanciado = false;

    void Start()
    {
        _gameController = FindObjectOfType(typeof(GameController)) as GameController;
    }

    // Update is called once per frame
    void Update()
    {
        if (_chaoInstanciado == false)
        {
            if(transform.position.x <= 0)
            {
                _chaoInstanciado = true;

                GameObject ObjetoTemporarioChao = Instantiate(_gameController._chaoPrefab);

                ObjetoTemporarioChao.transform.position = new Vector3((transform.position.x + _gameController._chaoTamanho), transform.position.y, transform.position.z) ;

                Debug.Log("O chão foi Instanciado");
            }
        }

        if(transform.position.x < _gameController._chaoDestruido)
        {
            Destroy(this.gameObject);
        }
    }

    private void FixedUpdate()
    {
        MoveChao();
    }

    void MoveChao()
    {
        transform.Translate(Vector2.left * _gameController._chaoVelocidade);
    }
}
