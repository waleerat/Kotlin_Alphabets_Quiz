package com.wgoweb.flagquiz

class QuizBrain {
    val AlphabetRows = arrayOf(
        arrayOf("alphabet_001", "ก", "ก ไก่", "ko kai", "chicken", "k", "k", "mid"),
        arrayOf("alphabet_002", "ข", "ข ไข่", "kho khai", "egg", "kh", "k", "high"),
        arrayOf("alphabet_003", "ฃ", "ฃ ขวด", "kho khuat", "bottle (obsolete)", "kh", "k", "high"),
        arrayOf("alphabet_004", "ค", "ค ควาย", "kho khwai", "buffalo", "kh", "k", "low"),
        arrayOf("alphabet_005", "ฅ", "ฅ คน", "kho khon", "person (obsolete)", "kh", "k", "low"),
        arrayOf("alphabet_006", "ฆ", "ฆ ระฆัง", "kho ra-khang", "bell", "kh", "k", "low"),
        arrayOf("alphabet_007", "ง", "ง งู", "ngo ngu", "snake", "ng", "ng", "low"),
        arrayOf("alphabet_008", "จ", "จ จาน", "cho chan", "plate", "ch", "t", "mid"),
        arrayOf("alphabet_009", "ฉ", "ฉ ฉิ่ง", "cho ching", "cymbals", "ch", " –", "high"),
        arrayOf("alphabet_010", "ช", "ช ช้าง", "cho chang", "elephant", "ch", "t", "low"),
        arrayOf("alphabet_011", "ซ", "ซ โซ่", "so so", "chain", "s", "t", "low"),
        arrayOf("alphabet_012", "ฌ", "ฌ เฌอ", "cho choe", "tree", "ch", " –", "low"),
        arrayOf("alphabet_013", "ญ", "ญ หญิง", "yo ying", "woman", "y", "n", "low"),
        arrayOf("alphabet_014", "ฎ", "ฎ ชฎา", "do cha-da", "headdress", "d", "t", "mid"),
        arrayOf("alphabet_015", "ฏ", "ฏ ปฏัก", "to pa-tak", "goad, javelin", "t", "t", "mid"),
        arrayOf("alphabet_016", "ฐ", "ฐ ฐาน", "tho than", "pedestal", "th", "t", "high"),
        arrayOf("alphabet_017","ฑ", "ฑ มณโฑ","tho montho","Montho, character from Ramayana","th or d", "low"),
        arrayOf("alphabet_018", "ฒ", "ฒ ผู้เฒ่า", "tho phu-thao", "elder", "th", "t", "low"),
        arrayOf("alphabet_019", "ณ", "ณ เณร", "no nen", "samanera", "n", "n", "low"),
        arrayOf("alphabet_020", "ด", "ด เด็ก", "do dek", "child", "d", "t", "mid"),
        arrayOf("alphabet_021", "ต", "ต เต่า", "to tao", "turtle", "t", "t", "mid"),
        arrayOf("alphabet_022", "ถ", "ถ ถุง", "tho thung", "sack", "th", "t", "high"),
        arrayOf("alphabet_023", "ท", "ท ทหาร", "tho thahan", "soldier", "th", "t", "low"),
        arrayOf("alphabet_024", "ธ", "ธ ธง", "tho thong", "flag", "th", "t", "low"),
        arrayOf("alphabet_025", "น", "น หนู", "no nu", "mouse", "n", "n", "low"),
        arrayOf("alphabet_026", "บ", "บ ใบไม้", "bo baimai", "leaf", "b", "p", "mid"),
        arrayOf("alphabet_027", "ป", "ป ปลา", "po pla", "fish", "p", "p", "mid"),
        arrayOf("alphabet_028", "ผ", "ผ ผึ้ง", "pho phueng", "bee", "ph", " –", "high"),
        arrayOf("alphabet_029", "ฝ", "ฝ ฝา", "fo fa", "lid", "f", " –", "high"),
        arrayOf("alphabet_030", "พ", "พ พาน", "pho phan", "phan", "ph", "p", "low"),
        arrayOf("alphabet_031", "ฟ", "ฟ ฟัน", "fo fan", "teeth", "f", "p", "low"),
        arrayOf("alphabet_032", "ภ", "ภ สำเภา", "pho sam-phao", "junk", "ph", "p", "low"),
        arrayOf("alphabet_033", "ม", "ม ม้า", "mo ma", "horse", "m", "m", "low"),
        arrayOf("alphabet_034", "ย", "ย ยักษ์", "yo yak", "giant, yaksha", "y", "[c])", "low"),
        arrayOf("alphabet_035", "ร", "ร เรือ", "ro ruea", "boat", "r", "n", "low"),
        arrayOf("alphabet_036", "ล", "ล ลิง", "lo ling", "monkey", "l", "n", "low"),
        arrayOf("alphabet_037", "ว", "ว แหวน", "wo waen", "ring", "w", "–[d])", "low"),
        arrayOf("alphabet_038", "ศ", "ศ ศาลา", "so sala", "pavilion, sala", "s", "t", "high"),
        arrayOf("alphabet_039", "ษ", "ษ ฤๅษี", "so rue-si", "hermit", "s", "t", "high"),
        arrayOf("alphabet_040", "ส", "ส เสือ", "so suea", "tiger", "s", "t", "high"),
        arrayOf("alphabet_041", "ห", "ห หีบ", "ho hip", "chest, box", "h", "–", "high"),
        arrayOf("alphabet_042", "ฬ", "ฬ จุฬา", "lo chu-la", "kite", "l", "n", "low"),
        arrayOf("alphabet_043", "อ", "อ อ่าง", "o ang", "basin", "–arrayOf(e)", " –", "mid"),
        arrayOf("alphabet_044", "ฮ", "ฮ นกฮูก", "ho nok-huk", "owl", "h", " –", "low"),
    )

    fun generateChoices(quizId: Int): Question {
        var numbers: ArrayList<Int> = ArrayList<Int>()
        for (i in 0..3) {
            var n: Int
            do {
                n = (0..43).random()
            } while (numbers.contains(n))
            numbers.add(n)
        }

        val correctAnswer = (0..3).random()
        val drawable = "@drawable/"+AlphabetRows[numbers[correctAnswer]][0]
        //val drawableResourceId: Int = this.getResources().getIdentifier("nameOfDrawable", "drawable", this.getPackageName());

        return Question(
            id = quizId,
            question = "What is this alphabet call?",
            drawable = drawable ,
           /* optionOne = AlphabetRows [numbers[0]][2] +" > "+ AlphabetRows[numbers[0]][3],
            optionTwo = AlphabetRows[numbers[1]][2] +" > "+  AlphabetRows[numbers[1]][3],
            optionThree = AlphabetRows[numbers[2]][2] +" > "+  AlphabetRows[numbers[2]][3],
            optionFour = AlphabetRows[numbers[3]][2] +" > "+  AlphabetRows[numbers[3]][3],*/
            optionOne = AlphabetRows[numbers[0]][3],
            optionTwo = AlphabetRows[numbers[1]][3],
            optionThree = AlphabetRows[numbers[2]][3],
            optionFour = AlphabetRows[numbers[3]][3],
            correctAnswer = correctAnswer
        )
    }
}