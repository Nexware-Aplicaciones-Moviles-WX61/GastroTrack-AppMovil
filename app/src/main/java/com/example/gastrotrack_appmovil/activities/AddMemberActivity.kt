package com.example.gastrotrack_appmovil.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gastrotrack_appmovil.R
import com.example.gastrotrack_appmovil.communication.MembersApiResponse
import com.example.gastrotrack_appmovil.communication.RoleApiResponse
import com.example.gastrotrack_appmovil.db.AppDatabase
import com.example.gastrotrack_appmovil.models.Role
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.gastrotrack_appmovil.network.MemberService
import com.example.gastrotrack_appmovil.network.RoleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddMemberActivity : AppCompatActivity() {

    private lateinit var spinnerRole: Spinner
    private var selectedRoleId: Int? = null
    private lateinit var memberService: MemberService
    private lateinit var roleService: RoleService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_member)
        spinnerRole = findViewById(R.id.spRole)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        roleService = retrofit.create(RoleService::class.java)
        memberService = retrofit.create(MemberService::class.java)


        loadRoles()


        val btnAccept = findViewById<Button>(R.id.btnCreateMember)
        btnAccept.setOnClickListener {
            registerMember()
        }


        val btnCancelAM = findViewById<Button>(R.id.btnCancelAM)
        btnCancelAM.setOnClickListener {
            finish()
        }
    }

    private fun loadRoles() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gastrotrack-backend.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val roleService = retrofit.create(RoleService::class.java)
        roleService.getRoles().enqueue(object : Callback<List<RoleApiResponse>> {
            override fun onResponse(call: Call<List<RoleApiResponse>>, response: Response<List<RoleApiResponse>>) {
                if (response.isSuccessful) {
                    val rolesApiResponse = response.body() ?: emptyList()

                    val roles = rolesApiResponse.map { it.toRole() }

                    populateRoleSpinner(roles)
                } else {
                    Toast.makeText(this@AddMemberActivity, "Error al cargar roles: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<RoleApiResponse>>, t: Throwable) {
                Toast.makeText(this@AddMemberActivity, "Error de red al cargar roles: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun populateRoleSpinner(roles: List<Role>) {
        val roleNames = roles.map { it.roleName }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roleNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter

        spinnerRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedRoleId = roles[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun registerMember() {
        val nameInput = findViewById<EditText>(R.id.etMemberName)
        val descriptionInput = findViewById<EditText>(R.id.etMemberDescription)
        val photoInput = findViewById<EditText>(R.id.etMemberPhoto)

        val memberName = nameInput.text.toString()
        val description = descriptionInput.text.toString()
        val photo = photoInput.text.toString()
        val roleId = selectedRoleId

        if (memberName.isNotEmpty() && description.isNotEmpty() && photo.isNotEmpty() && roleId != null) {
            val memberApiResponse = MembersApiResponse(
                memberName = memberName,
                description = description,
                photo = photo,
                roleId = roleId
            )

            memberService.createMember(memberApiResponse).enqueue(object : Callback<MembersApiResponse> {
                override fun onResponse(call: Call<MembersApiResponse>, response: Response<MembersApiResponse>) {
                    if (response.isSuccessful) {
                        val member = response.body()?.toMember()

                        if (member != null) {
                            val dao = AppDatabase.getDatabase(this@AddMemberActivity).membersDAO()
                            dao.insertMember(member)

                            Toast.makeText(this@AddMemberActivity, "Miembro registrado exitosamente", Toast.LENGTH_SHORT).show()

                            finish()
                        } else {
                            Toast.makeText(this@AddMemberActivity, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@AddMemberActivity, "Error al registrar miembro: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MembersApiResponse>, t: Throwable) {
                    Toast.makeText(this@AddMemberActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}