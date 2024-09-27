from app import test_app as ta
from selenium.webdriver.common.by import By
from login_galmo import perform_login, close


test_app = perform_login("EmailAqui", "SenhaAqui")


xpath = '//button[@title="Iniciador de aplicativos"]'
element = test_app.wait_presence(xpath=xpath, time=30)

if element is None:
    print("Elemento não encontrado!")
    close()

element.click()

xpath = '//input[contains(@placeholder, "Pesquisar aplicativos e itens…")]'
element = test_app.wait_presence(xpath=xpath, time=10)

if element is None:
    print("Elemento 2 não encontrado!")
    close()

test_app.insert_value(xpath=xpath, message="Leads")
insert_was_ok = test_app.wait_text_to_be_present(xpath=xpath, text="Leads")

# if not insert_was_ok:
#     print("Valor não foi inserido")
#     close()

test_app.enter()



# xpath = '//button[text()="Criar"]'
# element = test_app.wait_presence(xpath=xpath, time=10)
# element.click()

close()



