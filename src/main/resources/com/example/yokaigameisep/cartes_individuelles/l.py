
from pdf2image import convert_from_path, convert_from_bytes
import os


urls = [ elemName for elemName in os.listdir('.') if elemName.endswith('.pdf') ];


for elemName in urls:
	pdf_file = open(elemName, 'rb').read()
	image_png = convert_from_bytes(pdf_file)

	new_name = elemName.replace('.pdf', '.png')

	image_png[0].save(new_name, 'PNG')


print(urls)
