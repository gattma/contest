import PyPDF2
from textblob_de import TextBlobDE
import numpy as np


def open_pdf(filepath):
    pdffileobj = open(filepath, 'rb')
    return PyPDF2.PdfFileReader(pdffileobj)


def get_nouns_from_site(pdf, pagenr):
    pageobj = pdf.getPage(pagenr)
    text = pageobj.extractText()
    blob = TextBlobDE(text)
    return [n for n, t in blob.tags if t == 'NN']


def write_to_csv(text, outputdir):
    outpath = f"{outputdir}/dict.csv"
    np.savetxt(outpath, text, delimiter=";", fmt='%s')


def has_numbers(inputString):
    return any(char.isdigit() for char in inputString)


def only_lowercase(inputString):
    return inputString[1:len(inputString)].islower()


def extract_text(filepath, *sites):
    pdf = open_pdf(filepath)
    result = list()
    for site in sites:
        words = get_nouns_from_site(pdf, site)
        new_words = set(words) - set(result)
        result = result + list(new_words)

    # result.sort()
    return [word for word in result if (3 < len(word) < 30) and (word.isalpha()) and (only_lowercase(word))]


def run():
    words_eib_0 = extract_text("docs/GRI_Skriptum_teil1.pdf", 14, 16, 18, 22, 52)
    print(words_eib_0)

    words_eib_1 = extract_text("docs/T3_Links.pdf", 1, 2)
    print(words_eib_1)

    words_eir = extract_text("docs/Rechnerarchitektur_Skriptum.pdf", 3, 10, 20, 27, 38, 39)
    print(words_eir)

    words_lgi = extract_text("docs/uebung4.pdf", 0)
    print(words_lgi)

    words_qc = extract_text("docs/NI_chap12.pdf", 0, 2, 3)
    print(words_qc)

    write_to_csv(words_eib_0 + words_eib_1 + words_eir + words_lgi + words_qc, "generated")


if __name__ == '__main__':
    run()
