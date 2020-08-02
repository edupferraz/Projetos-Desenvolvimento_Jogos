using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerController : MonoBehaviour
{
    private Animator        playerAnimator;         //Animator
    private Rigidbody2D     playerRigidBody2D;      //RigidBody2D
    private SpriteRenderer  srPlayer;

    public  GameObject      playerDie;

    public  Transform       groundCheck;            //GroundCheck
    public  bool            isGround = false;       //IsGrounded

    public  float           speed;                  //Velocidade de andar

    public  float           touchRun = 0.0f;        //Touch para celular

    public  bool            facingRight = true;     //Virado para direita - Flip

    //Pulo

    public bool jump = false;

    public int numberJumps = 0;

    public int maximoJumps = 2;

    public float jumpForce;

    private GameController controleGame;

    public int vidas = 3;
    public Color hitColor;
    public Color noHitColor;

    private bool playerInvencivel = false;

    //cena

       



    // Start is called before the first frame update
    void Start()
    {
        playerAnimator = GetComponent<Animator>();
        playerRigidBody2D = GetComponent<Rigidbody2D>();
        srPlayer = GetComponent<SpriteRenderer>();

        controleGame = FindObjectOfType(typeof(GameController)) as GameController;
    }

    // Update is called once per frame
    void Update()
    {
        isGround = Physics2D.Linecast(transform.position, groundCheck.position, 1 << LayerMask.NameToLayer("Ground"));
        playerAnimator.SetBool("IsGrounded", isGround);

        Debug.Log(isGround.ToString());

        touchRun = Input.GetAxisRaw("Horizontal");
        Debug.Log(touchRun.ToString());

        if (Input.GetButtonDown("Jump")) // Tecla de movimentação pra cima - up
        {
            jump = true;
        }

        SetaMovimentos();
    }

    void MovePlayer(float movimentoH) //Função responsável pela movimentação e flip do personagem
    {
        playerRigidBody2D.velocity = new Vector2(movimentoH * speed, playerRigidBody2D.velocity.y);

        if (movimentoH < 0 && facingRight == true || (movimentoH > 0 && !facingRight == true))
        {
            Flip();
        }
    }

    private void FixedUpdate()
    {
        MovePlayer(touchRun);

        if (jump)
        {
            JumpPlayer();
            isGround = true;
        }
    }

    void JumpPlayer()
    {
        if (isGround)
        {
            numberJumps = 0;
        }

        if (isGround || numberJumps < maximoJumps)
        {
            playerRigidBody2D.AddForce(new Vector2(0f, jumpForce));

            isGround = false;

            numberJumps ++;

            controleGame.fxGame.PlayOneShot(controleGame.fxPulo);
        }

        jump = false;
    }

    void Flip() //Função responsável pelo flip - Direita e Esquerda
    {
        facingRight = !facingRight;

        Vector3 theScale = transform.localScale;

        theScale.x *= -1;

        transform.localScale = new Vector3(theScale.x, transform.localScale.y, transform.localScale.z);

        
    }

    void SetaMovimentos() //Função responsável pela relação de animação e ação do personagem
    {
        playerAnimator.SetBool("Walk", playerRigidBody2D.velocity.x != 0 && isGround); //true or false

        playerAnimator.SetBool("Jump", !isGround);
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        switch (collision.gameObject.tag)
        {
            case "Coletaveis":
                controleGame.Pontuacao(1);
                Destroy(collision.gameObject);
                break;

            case "Inimigo":

                //Animacao da explosao
                GameObject tempExplosao = Instantiate(controleGame.prefabExplosao, transform.position, transform.localRotation);
                Destroy(tempExplosao, 0.5f);

                //Adiciona força ao pulo
                Rigidbody2D rb = GetComponentInParent<Rigidbody2D>();
                rb.velocity = new Vector2(rb.velocity.x, 0);
                rb.AddForce(new Vector2(0, 300));

                //Som explosao
                controleGame.fxGame.PlayOneShot(controleGame.fxExplosao);

                //Destroi o Inimigo
                Destroy(collision.gameObject);

                break;
        }
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        switch (collision.gameObject.tag)
        {
            case "Inimigo":
                Hurt();
                break;
        }
    }

    void Hurt()
    {
        if (!playerInvencivel)
        {
            playerInvencivel = true;

            vidas -= 1; //vidas = vidas - 1
            Debug.Log("Perdeu uma vida");

            controleGame.BarraVida(vidas);

            StartCoroutine("Dano");

            if(vidas < 1)
            {
                GameObject pDieTemp = Instantiate(playerDie, transform.position, Quaternion.identity); // Quartenion.indetity é como desativar a rotação do eixo z

                Rigidbody2D rbDie = pDieTemp.GetComponent<Rigidbody2D>();

                rbDie.AddForce(new Vector2(150f, 500f));

                gameObject.SetActive(false);

                controleGame.fxGame.PlayOneShot(controleGame.fxDie);

                Invoke("CarregaJogo", 4f);
            }
        }
        
    }

    void CarregaJogo()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    IEnumerator Dano()
    {
        srPlayer.color = noHitColor;
        controleGame.fxGame.PlayOneShot(controleGame.fxDie);
        yield return new WaitForSeconds(0.1f);

        for (float i = 0; i<0.7; i += 0.1f)
        {
            srPlayer.enabled = false;
            yield return new WaitForSeconds(0.1f);
            srPlayer.enabled = true;
            yield return new WaitForSeconds(0.1f);
        }

        srPlayer.color = Color.white;

        playerInvencivel = false;

        
    }
}
