function htmlFontSize() {
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var width = w > h ? h : w;
    width = width > 720 ? 720 : width
    var fz = ~~(width * 100000 / 36) / 10000
    document.getElementsByTagName("html")[0].style.cssText = 'font-size: ' + fz + "px";
    var realfz = ~~(+window.getComputedStyle(document.getElementsByTagName("html")[0]).fontSize.replace('px', '') * 10000) / 10000
    if (fz !== realfz) {
        document.getElementsByTagName("html")[0].style.cssText = 'font-size: ' + fz * (fz / realfz) + "px";
    }
}
htmlFontSize();
// document.getElementById('tr_subledger').innerHTML();
function pushNewsDate(dataText) {
    // document.write(dataText);
    console.log(dataText);
    var dom = document.getElementById('cont_top');
    dom.innerHTML = dataText;
}
var str = `
            <div class="article-content">
                <img src="http://i0.pstatp.com/large/9b1400073a3317c3f688" img_width="529" img_height="371" alt="美301调查指责中国“占便宜” 外交部：美国一直做亏本买卖？"
                    inline="0">
                <p>在今天举行的外交部记者会上,有记者提问:美国在《关于301调查的声明》中指责中国长期从事不公平贸易,导致中国占便宜、美国吃亏。外交部发言人对此有何回应?</p>
                <p>外交部发言人华春莹回答:中国商务部上周已经发表声明,批驳了美方有关错误观点,指出美方有关指责完全是歪曲事实、站不住脚。我想再问美方几个问题:</p>
                <p>第一,众所周知,美国是世界贸易规则主要制定者,美元是国际贸易主要结算货币,而中国是国际贸易后来者、WTO规则接受者。大家有谁相信,规则制定者制定的规则是利人不利己的?</p>
                <p>第二,过去40年间,中美贸易规模增长了230多倍,去年两国贸易总量已接近6000亿美元,这既是经济规律使然,也是中美经贸合作互利共赢的必然。大家都知道,生意人不做亏本买卖,资本从来都是逐利的。难道美国在过去40年里一直和中国做亏本买卖吗?美方可不可以请那些长期和中国做生意的美国企业出来说句公道话?</p>
                <p>第三,今天中国已成为120多个国家和地区的最大贸易伙伴,是世界上增长最快的主要出口市场,全球吸引外资最多的发展中国家。去年中国吸引外资居世界第二位,今年上半年中国新设立外商投资企业同比增长96.6%。难道这么多国家都在并将继续与中国做亏本生意吗?</p>
                <p>我想指出,贸易不平衡不等于不公平。公平要靠大家平等协商来制订国际规则,而不能自说自话,根据自身利益来制定标准,甚至以牺牲其他国家公平和利益为代价攫取自身利益。如果说,当年中国入世谈判时,WTO规则的“篮筐”不会为中国而降低,那么今天,经济全球化大潮也绝不会因为某个国家包括美方有些人逆潮流而动而退回到一个个自我孤立的小河小湖。在今天这个各国相互依存、命运与共的时代里,保护主义保护不了自己,单边主义只会损人害己。</p>
            </div>
        `
pushNewsDate(str)